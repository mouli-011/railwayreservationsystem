package controller

import database.Database
import model.CoachName
import model.ConstantStrings
import model.Passenger
import model.Train
import view.BookTicketsMenuPage
import view.OperationResult
import java.time.LocalDateTime

class TicketBooker: FareCalculator,SeatNumberGenerator {
   fun bookTickets(userId: String): OperationResult {
       val train: Train?
       val ticketController = TicketController()
       val stationController = StationController()
       val trainController = TrainController()
       val paymentController = PaymentController()
       val stationInfo = stationController.searchStation()
       val dateOfTravel = BookTicketsMenuPage.getDateOfTravel()
       var operationResult:OperationResult = OperationResult.Failure
       val availableTrainIds = trainController.getAvailableTrains(stationInfo.first, stationInfo.second, dateOfTravel)
       availableTrainIds.forEach { trainController.searchTrainByTrainNumber(it) }
       if (availableTrainIds.isNotEmpty()) {
           val trainNumberChosen = BookTicketsMenuPage.getTrainNumber(availableTrainIds)
           val coachDetails = mutableMapOf<CoachName, Int>()
           train = Database.getAllTrains().firstOrNull{ it.trainNumber == trainNumberChosen}?.let {
               it.coaches.forEach{ coach ->
                   coachDetails[coach.coachName] = coach.availableSeatsInCoach
               }
               it
           }
           train?.let {
               val coachChosen: CoachName = BookTicketsMenuPage.getCoach(coachDetails)
               val noOfTickets = BookTicketsMenuPage.getNoOfTickets()
               val isValidNumberOfTickets = noOfTickets>0
               val areTicketsAvailable = trainController.getAvailableSeatsInCoach(trainNumberChosen, coachChosen) >= noOfTickets
               if (isValidNumberOfTickets&&areTicketsAvailable) {
                   val passengers = mutableListOf<Passenger>()
                   for (i in 0 until noOfTickets) {
                       val passengerDetails = BookTicketsMenuPage.getPassengerDetails()
                       passengers.add(
                           Passenger(
                               passengerDetails[ConstantStrings.PASSENGERNAME.message] ?: ConstantStrings.DUMMY.message,
                               passengerDetails[ConstantStrings.PASSENGERAGE.message]?.toShort() ?: 0,
                               getSeatNumber(
                                   trainNumberChosen,
                                   SeatCalculator.getAvailableSeats(trainNumberChosen) - i,
                                   coachChosen
                               )
                           )
                       )
                   }
                   val fare = (calculateFare(trainNumberChosen, stationInfo.first, stationInfo.second, coachChosen) * noOfTickets)
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
                       operationResult = trainController.addTrainToSet(train.trainName, updatedCoachesSet, train.route, train.trainNumber)
                   }?: println("Payment Not Done")
               }
               else{
                   println("Sorry Only ${trainController.getAvailableSeatsInCoach(trainNumberChosen, coachChosen)} seats available!!")
               }
           }
       }
       else{
           println("Sorry No Trains Available!!")
       }
       return operationResult
   }
}
