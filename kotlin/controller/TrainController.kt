package controller

import database.Database
import model.*
import view.BookTicketsMenuPage
import view.OperationResult
import view.TrainView
import java.time.LocalDate
import java.time.LocalTime

class TrainController {
    fun addTrainToSet(
        trainName: String,
        coaches: Set<Coach>,
        route: List<Connection>,
        trainNumber: Int? = null,
    ): OperationResult {
        val train = if (trainNumber != null)
            Train(trainName, coaches, route, trainNumber)
        else
            Train(trainName, coaches, route)
        return Database.addTrain(train)
    }

    fun createRoute(routeDetails: Map<Int, DaysTime>): List<Connection> {
        val route = mutableListOf<Connection>()
        val stationIds = routeDetails.keys
        stationIds.forEach { stationId ->
            Database.getStation(stationId)?.let { station ->
                route.add(
                    Connection(
                        station,
                        routeDetails[stationId] ?: DaysTime(LocalTime.parse("00:00"), setOf(Days.SATURDAY))
                    )
                )
            }
        }
        return route
    }

    fun createCoach(coachDetails: List<Map<String, String>>): Set<Coach> {
        val coaches = mutableSetOf<Coach>()
        coachDetails.forEach { coach ->
            coaches.add(
                Coach(
                    when (coach[ConstantStrings.COACHNAME.message]) {
                        CoachName.SLEEPER.name -> CoachName.SLEEPER
                        CoachName.SEATER.name -> CoachName.SEATER
                        CoachName.AC.name -> CoachName.AC
                        else -> CoachName.SEATER
                    },
                    coach[ConstantStrings.NUMBEROFCOMPARTMENTS.message]?.toInt() ?: 0,
                    coach[ConstantStrings.TOTALSEATSINCOACH.message]?.toInt() ?: 0,
                    coach[ConstantStrings.AVAILABLETICKETSINCOACH.message]?.toInt() ?: coach[ConstantStrings.TOTALSEATSINCOACH.message]?.toInt() ?: 0,
                    coach[ConstantStrings.FAREPERKM.message]?.toDouble() ?: 0.0
                )
            )
        }
        return coaches
    }

    fun removeTrainFromSet(trainNumber: Int): OperationResult = Database.removeTrain(trainNumber)
    fun getAvailableSeatsInCoach(trainNumber: Int, coachName: CoachName): Int =
        Database.getTrain(trainNumber)?.coaches?.firstOrNull { coach -> coach.coachName == coachName }?.availableSeatsInCoach
            ?: -1

    fun getAvailableTrains(
        startingStationId: Int,
        destinationStationId: Int,
        dateOfTravel: LocalDate
    ): MutableSet<Int> {
        val availableTrains = mutableSetOf<Int>()
        Database.getAllTrains().forEach { train ->
            if (SeatCalculator.getAvailableSeats(train.trainNumber) > 0) {
                var hasStartingStation = false
                var hasDestinationStation = false
                var startingStationIndex: Int = -1
                train.route.forEach {
                    if (it.station.stationId == startingStationId) {
                        hasStartingStation = true
                        startingStationIndex = train.route.indexOf(it)
                    }
                    if (hasStartingStation) {
                        if ((it.station.stationId == destinationStationId) && (train.route.indexOf(it) > startingStationIndex)) {
                            hasDestinationStation = true
                        }
                    }
                }
                if (hasStartingStation && hasDestinationStation) {
                    train.route.forEach {
                        if ((it.station.stationId == startingStationId) && it.arrivalDaysTime.days.contains(
                                dateOfTravel.dayOfWeek.toString().toDay()
                            )
                        ) {
                            availableTrains.add(train.trainNumber)
                        }
                    }
                }
            }
        }
        return availableTrains
    }

    fun updateCoachAvailability(trainNumber: Int, coachName: CoachName, totalNoOfTickets: Int): Set<Coach> {
        val updatedCoaches = mutableSetOf<Coach>()
        Database.getTrain(trainNumber)?.coaches?.forEach { coach ->
            if (coach.coachName == coachName) {
                updatedCoaches.add(
                    Coach(
                        coach.coachName,
                        coach.noOfCompartments,
                        coach.totalSeatsInCoach,
                        coach.availableSeatsInCoach - totalNoOfTickets,
                        coach.farePerKm
                    )
                )
            } else {
                updatedCoaches.add(coach)
            }
        }
        return updatedCoaches
    }

    fun searchTrainByTrainNumber(trainNumber: Int) {
        Database.getTrain(trainNumber)?.let { train ->
            TrainView.displayTrains(setOf(train))
        }
    }
    fun searchTrain() {
        val stationController = StationController()
        val stationInfo = stationController.searchStation()
        val dateOfTravel = BookTicketsMenuPage.getDateOfTravel()
        val availableTrainIds = getAvailableTrains(stationInfo.first, stationInfo.second, dateOfTravel)
        if (availableTrainIds.isNotEmpty()) {
            availableTrainIds.forEach { searchTrainByTrainNumber(it) }
        } else {
            println("Sorry No Trains Available")
        }
    }

    fun viewAllTrains() {
        val trains = Database.getAllTrains()
        if (trains.isNotEmpty()) {
            TrainView.displayTrains(trains)
        } else {
            println("No Trains Found")
        }
    }

    fun getTrainArrivalTime(trainNumber: Int, stationId: Int): LocalTime =
        Database.getTrain(trainNumber)?.route?.firstOrNull { it.station.stationId == stationId }?.arrivalDaysTime?.time
            ?: LocalTime.parse("00:00")
}