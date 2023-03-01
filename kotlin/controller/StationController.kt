package controller

import database.Database
import model.Station
import view.OperationResult
import view.SearchStationMenuPage
import view.StationView

class StationController {

    fun addStationToSet(stationName: String, city: String, distanceFromCapeComorin: Long): OperationResult {
        val station = Station(stationName, city, distanceFromCapeComorin)
        return Database.addStation(station)
    }

    fun searchStation(): Pair<Int, Int> {
        val travelInfo = SearchStationMenuPage.getStationInfo()
        Database.getAllStations().forEach { station ->
            val hasStartingStation: Boolean =
                station.stationName.contains(travelInfo["startingStationName"] ?: "", true)
            val hasDestinationStation: Boolean =
                station.stationName.contains(travelInfo["destinationStationName"] ?: "", true)
            if (hasStartingStation || hasDestinationStation) {
                StationView.displayStation(setOf(station))
            }
        }
        val stationIds = SearchStationMenuPage.getStationID()
        return Pair(stationIds["startingStationId"] ?: -1, stationIds["destinationStationId"] ?: -1)
    }

    fun viewAllStations() {
        if (Database.getAllStations().isNotEmpty()) {
            StationView.displayStation(Database.getAllStations())
        } else {
            println("No Stations Found")
        }
    }
}
