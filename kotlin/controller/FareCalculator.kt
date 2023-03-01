package controller

import database.Database
import model.CoachName
import kotlin.math.absoluteValue

interface FareCalculator {
    fun calculateFare(trainNumber: Int,startingStationId: Int,destinationStationId: Int,coachName: CoachName): Double{
        var distanceFromCapeComorinOfStartingStation = 0L
        var distanceFromCapeComorinOfDestinationStation = 0L
        var farePerKm = 0.0
        Database.getTrain(trainNumber)?.let{train ->
            with(train){
                distanceFromCapeComorinOfStartingStation = route.firstOrNull { it.station.stationId == startingStationId }?.station?.distanceFromCapeComorin?:0L
                distanceFromCapeComorinOfDestinationStation = route.firstOrNull{ it.station.stationId == destinationStationId }?.station?.distanceFromCapeComorin?:0L
                farePerKm = coaches.first{ it.coachName == coachName}.farePerKm
            }
        }
        return ((distanceFromCapeComorinOfDestinationStation-distanceFromCapeComorinOfStartingStation).absoluteValue)*farePerKm
    }
}