package view

import model.Coach
import model.Connection
import model.Days
import model.Train

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
    fun displayTrains(trains: Set<Train>){
        trains.forEach { train ->
            with(train){
                println()
                println(train.toString())
                displayRoutes(route)
                displayCoaches(coaches)
            }
        }
    }
    private fun displayRoutes(routes: List<Connection>){
        routes.forEach { connection ->
            with(connection){
                println()
                println(connection.toString())
                displayWorkingDays(arrivalDaysTime.days)
            }
        }
    }
    private fun displayCoaches(coaches: Set<Coach>){
        coaches.forEach { coach ->
            println()
            println(coach.toString())
        }
    }
}