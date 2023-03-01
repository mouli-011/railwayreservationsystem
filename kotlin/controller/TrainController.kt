package controller

import database.Database
import model.*
import view.BookTicketsMenuPage
import view.OperationResult
import view.TrainView
import java.time.LocalDate
import java.time.LocalTime

class TrainController{
    private lateinit var train: Train
    private val stationController = StationController()
    fun addTrainToSet(
        trainName: String,
        coaches: Set<Coach>,
        route: List<Connection>,
        trainNumber: Int? = null,
    ): OperationResult {
        train = if (trainNumber != null)
            Train(trainName, coaches, route, trainNumber)
        else
            Train(trainName, coaches, route)
        return when (Database.trains.add(train)) {
            true -> OperationResult.Success
            false -> OperationResult.Failure
        }
    }

    fun createRoute(routeDetails: Map<Int, DaysTime>): List<Connection> {
        val route = mutableListOf<Connection>()
        val stationIds = routeDetails.keys
        for (stationId in stationIds) {
            for (station in Database.stations) {
                if (stationId == station.stationId) {
                    route.add(Connection(station, routeDetails[stationId]?:DaysTime(LocalTime.parse("00:00"),setOf(Days.SATURDAY))))
                }
            }
        }

        return route
    }

    fun createCoach(coachDetails: List<Map<String, String>>): Set<Coach> {
        val coaches = mutableSetOf<Coach>()
        for (coach in coachDetails) {
            coaches.add(
                Coach(
                    when (coach["coachName"]) {
                        "SLEEPER" -> CoachName.SLEEPER
                        "SEATER" -> CoachName.SEATER
                        "AC" -> CoachName.AC
                        else -> CoachName.SEATER
                    },
                    coach["noOfCompartments"]?.toInt() ?: 0,
                    coach["totalSeatsInCoach"]?.toInt() ?: 0,
                    coach["availableTicketsInCoach"]?.toInt() ?: coach["totalSeatsInCoach"]?.toInt() ?: 0,
                    coach["farePerKm"]?.toDouble() ?: 0.0
                )
            )
        }
        return coaches
    }

    fun removeTrainFromSet(trainNumber: Int): OperationResult {
        return when (Database.trains.removeIf { it.trainNumber == trainNumber }) {
            true -> OperationResult.Success
            false -> OperationResult.Failure
        }
    }

    fun getAvailableSeatsInCoach(trainNumber: Int, coachName: CoachName): Int {
        for (train in Database.trains) {
            if (train.trainNumber == trainNumber) {
                for (coach in train.coaches) {
                    if (coach.coachName == coachName) {
                        return coach.availableSeatsInCoach
                    }
                }
            }
        }
        return -1
    }

    fun getAvailableTrains(startingStationId: Int, destinationStationId: Int, dateOfTravel: LocalDate): MutableSet<Int> {
        val availableTrains = mutableSetOf<Int>()
        for (train in Database.trains) {
            if (SeatCalculator.getAvailableSeats(train.trainNumber) > 0) {

                var hasStartingStation = false
                var hasDestinationStation = false
                var startingStationIndex:Int = -1
                train.route.forEach {
                    if(it.station.stationId == startingStationId){
                        hasStartingStation = true
                        startingStationIndex = train.route.indexOf(it)
                }
                    if(hasStartingStation) {
                        if ((it.station.stationId == destinationStationId) && (train.route.indexOf(it) > startingStationIndex)) {
                            hasDestinationStation = true
                        }
                    }
                }
                if(hasStartingStation&&hasDestinationStation) {
                    train.route.forEach {
                        if((it.station.stationId == startingStationId) && it.arrivalDaysTime.days.contains(dateOfTravel.dayOfWeek.toString().toDay())){
                            availableTrains.add(train.trainNumber)
                        }
                    }
                }
            }
        }
        return availableTrains
    }
    fun updateCoachAvailability(trainNumber: Int,coachName: CoachName,totalNoOfTickets: Int): Set<Coach>{
        val updatedCoaches = mutableSetOf<Coach>()
        for(train in Database.trains){
            if(train.trainNumber == trainNumber){
               for(coach in train.coaches){
                   if(coach.coachName == coachName){
                       updatedCoaches.add(Coach(coach.coachName,coach.noOfCompartments,coach.totalSeatsInCoach,coach.availableSeatsInCoach-totalNoOfTickets,coach.farePerKm))
                   }
                   else{
                       updatedCoaches.add(coach)
                   }
               }
            }
        }
        return updatedCoaches
    }
    fun searchTrainByTrainNumber(trainNumber: Int){
        for(train in Database.trains){
            if(train.trainNumber == trainNumber){
                with(TrainView){
                    displayTrain(train.trainName,train.trainNumber)
                    train.route.forEach { displayRoute(it.station.stationId,it.station.stationName,it.station.city,it.arrivalDaysTime) }
                    train.coaches.forEach { displayCoach(it.coachName,it.noOfCompartments,it.totalSeatsInCoach,it.availableSeatsInCoach,it.farePerKm) }
                }
            }
        }
    }
    fun searchTrain(){
        val stationInfo = stationController.searchStation()
        val dateOfTravel = BookTicketsMenuPage.getDateOfTravel()
        val availableTrainIds = getAvailableTrains(stationInfo.first,stationInfo.second,dateOfTravel)
        if(availableTrainIds.size>0) {
            for (availableTrainId in availableTrainIds) {
                searchTrainByTrainNumber(availableTrainId)
            }
        }
        else{
            println("Sorry No Trains Available")
        }
    }
    fun viewAllTrains(){
        if(Database.trains.size>0) {
            for (train in Database.trains) {
                with(TrainView) {
                    displayTrain(train.trainName, train.trainNumber)
                    println("Route: \n")
                    train.route.forEach {
                        displayRoute(
                            it.station.stationId,
                            it.station.stationName,
                            it.station.city,
                            it.arrivalDaysTime
                        )
                    }
                    train.coaches.forEach {
                        displayCoach(
                            it.coachName,
                            it.noOfCompartments,
                            it.totalSeatsInCoach,
                            it.availableSeatsInCoach,
                            it.farePerKm
                        )
                    }
                }
            }
        }
        else{
            println("No Trains Found")
        }
    }
    fun getTrainArrivalTime(trainNumber: Int,stationId: Int): LocalTime{
        for(train in Database.trains){
            if(train.trainNumber == trainNumber){
                for(connection in train.route){
                    if(connection.station.stationId == stationId){
                        return connection.arrivalDaysTime.time
                    }
                }
            }
        }
        return LocalTime.parse("00:00")
    }
}
