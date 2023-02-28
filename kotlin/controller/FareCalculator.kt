package controller

import database.Database
import model.CoachName

interface FareCalculator {
    fun calculateFare(trainNumber: Int,startingStationId: Int,destinationStationId: Int,coachName: CoachName): Double{
        var noOfStationsTravelled = 0
        var startingStationFound = false
        var farePerStation = 0.0
        for(train in Database.trains){
            if(train.trainNumber == trainNumber){
                for(connection in train.route){
                    if(connection.station.stationId == destinationStationId){
                        break
                    }
                    if(connection.station.stationId == startingStationId){
                        startingStationFound = true
                    }
                    if(startingStationFound){
                        noOfStationsTravelled++
                    }
                }
            }
            for(coach in train.coaches){
                if (coachName == coach.coachName){
                    farePerStation = coach.farePerStation
                }
            }
        }
        return noOfStationsTravelled*farePerStation
    }
}