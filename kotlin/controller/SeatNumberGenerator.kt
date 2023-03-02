package controller

import model.CoachName
import model.ConstantStrings

interface SeatNumberGenerator {
    fun getSeatNumber(trainNumber: Int,availableSeats: Int,coachName: CoachName): String = when(coachName){
        CoachName.SLEEPER -> availableSeats.toString() + ConstantStrings.SLEEPER.message
        CoachName.SEATER -> availableSeats.toString() + ConstantStrings.SEATER.message
        CoachName.AC -> availableSeats.toString() + ConstantStrings.AC.message
    }
}