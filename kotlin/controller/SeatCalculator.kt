package controller

import database.Database

object SeatCalculator {
    fun getAvailableSeats(trainNumber: Int): Int{
        var availableSeats = 0
        Database.getTrain(trainNumber)?.let {
            it.coaches.forEach { coach ->
                availableSeats += coach.availableSeatsInCoach
            }
        }
        return availableSeats
    }
    /*
    fun getTotalSeats(trainNumber: Int): Int{
        var totalSeats = 0
        for(train in Database.trains) {
            if(train.trainNumber == trainNumber) {
                for (coach in train.coaches) {
                    totalSeats += coach.totalSeatsInCoach
                }
            }
        }
        return totalSeats
    }
     */
}