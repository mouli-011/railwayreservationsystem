package view

import model.Station

object StationView {
    fun displayStation(stations: Set<Station>){
        stations.forEach { station ->
            with(station){
                println("Station Name: $stationName\t StationId: $stationId\t City: $city\t Distance From Cape Comorin: $distanceFromCapeComorin\n\n")
            }
        }
    }
}