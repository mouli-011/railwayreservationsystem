package view

import model.Station

object StationView {
    fun displayStation(stations: Set<Station>){
        stations.forEach { station ->
            println()
                println(station.toString())
        }
    }
}