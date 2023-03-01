package view

import model.CoachName
import model.Days
import model.DaysTime

object TrainView {
    private fun displayWorkingDays(workingDays: Set<Days>){
        println("Arriving Days: ")
        for(day in workingDays){
            when(day){
                Days.MONDAY -> print("MON ")
                Days.TUESDAY -> print("TUE ")
                Days.WEDNESDAY -> print("WED ")
                Days.THURSDAY -> print("THURS ")
                Days.FRIDAY -> print("FRI ")
                Days.SATURDAY -> print("SAT ")
                Days.SUNDAY -> print("SUN ")
            }
        }
        println()
    }
    fun displayTrain(trainName: String, trainNumber: Int){
        println("Train Number: $trainNumber")
        println("Train Name: $trainName")
    }
    fun displayCoach(coachName: CoachName,noOfCompartments: Int,totalSeatsInCoach: Int,availableSeatsInCoach: Int,farePerKm:Double){
        println("Coach Name: $coachName")
        println("Number Of Compartments: $noOfCompartments")
        println("Total Seats In Coach: $totalSeatsInCoach")
        println("Available Seats In Coach: $availableSeatsInCoach")
        println("Fare Per Km: $farePerKm")
    }
    fun displayRoute(stationId: Int, stationName:String, city: String, arrivalDaysTime: DaysTime){
        println("Station Id: $stationId")
        println("Station Name: $stationName")
        println("City: $city")
        println("Train Arriving Time: ${arrivalDaysTime.time}")
        displayWorkingDays(arrivalDaysTime.days)
    }
}