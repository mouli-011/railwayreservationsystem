package controller

import model.CoachName

interface SeatNumberGenerator {
    fun getSeatNumber(trainNumber: Int,availableSeats: Int,coachName: CoachName): String = when(coachName){
        CoachName.SLEEPER -> "${availableSeats}SL"
        CoachName.SEATER -> "${availableSeats}SE"
        CoachName.AC -> "${availableSeats}AC"
    }
}