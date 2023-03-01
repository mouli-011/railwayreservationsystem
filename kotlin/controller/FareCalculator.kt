package controller

import database.Database
import model.CoachName
import kotlin.math.absoluteValue

interface FareCalculator {
    fun calculateFare(trainNumber: Int,startingStationId: Int,destinationStationId: Int,coachName: CoachName): Double{
        var distanceFromCapeComorinOfStartingStation = 0L
        var distanceFromCapeComorinOfDestinationStation = 0L
        var farePerKm = 0.0
        for(train in Database.trains){
            if(train.trainNumber == trainNumber){
                for(connection in train.route){
                    if(connection.station.stationId == startingStationId){
                        distanceFromCapeComorinOfStartingStation = connection.station.distanceFromCapeComorin
                    }
                    else if(connection.station.stationId == destinationStationId){
                        distanceFromCapeComorinOfDestinationStation = connection.station.distanceFromCapeComorin
                    }
                }
            }
            for(coach in train.coaches){
                if (coachName == coach.coachName){
                    farePerKm = coach.farePerKm
                }
            }
        }
        return ((distanceFromCapeComorinOfDestinationStation-distanceFromCapeComorinOfStartingStation).absoluteValue)*farePerKm
    }
}