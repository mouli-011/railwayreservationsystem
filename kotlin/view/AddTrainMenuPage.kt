package view

import model.Days
import model.DaysTime
import java.time.LocalTime

object AddTrainMenuPage {
    fun getTrainName(): String = GetInput.getStringInput { println("Enter Train Name: ") }
    fun getRoute(): Map<Int, DaysTime>{
        println("Refer Station Id From Above Table")
        val route = mutableMapOf<Int,DaysTime>()
        var stationId:Int
        while(true){
            stationId = GetInput.getIntInput { println("Enter Station Id: \nEnter -1 To Finish") }
            val workingDays = mutableSetOf<Days>()
            when(stationId){
                -1 -> break
                else -> {
                    println("Enter Arriving Days")
                    while(true) {
                        workingDays.add(GetInput.getDay())
                        if(GetInput.getStringInput { println("Press Any Key To Add More Days\nEnter f to stop adding days") } == "f")
                            break
                    }
                    route[stationId] = DaysTime(LocalTime.parse(GetInput.getStringInput { println("Enter Arrival Time: ")}),workingDays)
                }
            }
        }
        return route
    }
    fun getCoachDetails(): Map<String,String>{
        var coachName:String
        while(true) {
            coachName = GetInput.getStringInput { println("Enter Coach Name: ") }
            when (coachName) {
                "SLEEPER","SEATER","AC" -> break
                else -> println("Enter Valid Coach Name!!!")
            }
        }
        val noOfCompartments = GetInput.getStringInput { println("Enter number of compartments: ")}
        val totalSeatsInCoach = GetInput.getStringInput{ println("Enter total seats: ") }
        val farePerStation = GetInput.getStringInput{ println("Enter Fare Per Station") }
        return mapOf("coachName" to coachName,"noOfCompartments" to noOfCompartments,"totalSeatsInCoach" to totalSeatsInCoach,"farePerStation" to farePerStation)
    }
    fun getNextValue(): String = GetInput.getStringInput { println("Enter -1 to Exit or Any key to Add More: ") }
}