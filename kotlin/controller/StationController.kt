package controller

import database.Database
import model.Station
import view.OperationResult
import view.SearchStationMenuPage
import view.StationView

class StationController {
    private lateinit var station: Station

    fun addStationToSet(stationName:String, city:String,distanceFromCapeComorin: Long): OperationResult{
        station = Station(stationName,city,distanceFromCapeComorin)
        return when(Database.stations.add(station)){
            true -> OperationResult.Success
            false -> OperationResult.Failure
        }
    }
    fun searchStation(): Pair<Int,Int>{
        val travelInfo = SearchStationMenuPage.getStationInfo()
        for (station in Database.stations){
            if ((station.stationName.contains(travelInfo["startingStationName"]?:"",true))||((station.stationName.contains(travelInfo["destinationStationName"]?:"",true)))){
                StationView.displayStation(station.stationName, station.city,station.stationId,station.distanceFromCapeComorin)
            }
        }
        val stationIds = SearchStationMenuPage.getStationID()
        return Pair(stationIds["startingStationId"]?:-1,stationIds["destinationStationId"]?:-1)
    }
    fun viewAllStations(){
        if(Database.stations.size>0) {
            for (station in Database.stations) {
                StationView.displayStation(station.stationName, station.city, station.stationId,station.distanceFromCapeComorin)
            }
        }
        else{
            println("No Stations Found")
        }
    }
}