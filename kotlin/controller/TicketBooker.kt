package controller

import database.Database
import model.CoachName
import model.Passenger
import model.Train
import view.BookTicketsMenuPage
import view.OperationResult
import java.time.LocalDateTime

class TicketBooker: FareCalculator,SeatNumberGenerator {
    private lateinit var train: Train
    private val ticketController = TicketController()
    private val stationController = StationController()
    private val trainController = TrainController()
    private val paymentController = PaymentController()

   fun bookTickets(userId: String): OperationResult {
       val stationInfo = stationController.searchStation()
       val dateOfTravel = BookTicketsMenuPage.getDateOfTravel()
       var operationResult:OperationResult = OperationResult.Failure
       val availableTrainIds = trainController.getAvailableTrains(stationInfo.first, stationInfo.second, dateOfTravel)
       for (availableTrainId in availableTrainIds) {
           trainController.searchTrainByTrainNumber(availableTrainId)
       }
       if (availableTrainIds.size > 0) {
           val trainNumberChosen = BookTicketsMenuPage.getTrainNumber(availableTrainIds)
           val coachDetails = mutableMapOf<CoachName, Int>()
           for (train in Database.trains) {
               if (train.trainNumber == trainNumberChosen) {
                   this.train = Train(train.trainName, train.coaches, train.route, train.trainNumber)
                   for (coach in train.coaches) {
                       coachDetails[coach.coachName] = coach.availableSeatsInCoach
                   }
               }
           }
           val coachChosen: CoachName = BookTicketsMenuPage.getCoach(coachDetails)
           val noOfTickets = BookTicketsMenuPage.getNoOfTickets()
           if ((trainController.getAvailableSeatsInCoach(trainNumberChosen, coachChosen) >= noOfTickets)&&(noOfTickets>0)) {
               val passengers = mutableListOf<Passenger>()
               for (i in 0 until noOfTickets) {
                   val passengerDetails = BookTicketsMenuPage.getPassengerDetails()
                   passengers.add(
                       Passenger(
                           passengerDetails["passengerName"] ?: "",
                           passengerDetails["passengerAge"]?.toShort() ?: 0,
                           getSeatNumber(
                               trainNumberChosen,
                               SeatCalculator.getAvailableSeats(trainNumberChosen) - i,
                               coachChosen
                           )
                       )
                   )
               }
               val fare =
                   (calculateFare(trainNumberChosen, stationInfo.first, stationInfo.second, coachChosen) * noOfTickets)
               val payment = paymentController.makePayment(fare)
               payment?.let {
                   ticketController.addTicketToSet(
                       userId,
                       noOfTickets,
                       trainNumberChosen,
                       stationInfo.first,
                       stationInfo.second,
                       payment,
                       passengers,
                       LocalDateTime.of(
                           dateOfTravel,
                           trainController.getTrainArrivalTime(trainNumberChosen, stationInfo.first)
                       )
                   )
                   val updatedCoachesSet =
                       trainController.updateCoachAvailability(trainNumberChosen, coachChosen, noOfTickets)
                   trainController.removeTrainFromSet(trainNumberChosen)
                   operationResult = when(trainController.addTrainToSet(train.trainName, updatedCoachesSet, train.route, train.trainNumber)){
                       OperationResult.Failure -> OperationResult.Failure
                       OperationResult.Success -> OperationResult.Success
                   }
               }
           }
           else{
               println("Sorry Only ${trainController.getAvailableSeatsInCoach(trainNumberChosen, coachChosen)} seats available!!")
           }
       }
       else{
           println("Sorry No Trains Available!!")
       }
       return operationResult
   }
}
