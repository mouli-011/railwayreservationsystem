package controller

import database.Database
import model.Station
import view.OperationResult
import view.SearchStationMenuPage
import view.StationView

class StationController {
    private lateinit var station: Station

    fun addStationToSet(stationName:String, city:String): OperationResult{
        station = Station(stationName,city)
        return when(Database.stations.add(station)){
            true -> OperationResult.Success
            false -> OperationResult.Failure
        }
    }
    fun searchStation(): Pair<Int,Int>{
        val travelInfo = SearchStationMenuPage.getStationInfo()
        for (station in Database.stations){
            if ((station.stationName.contains(travelInfo["startingStationName"]?:"",true))||((station.stationName.contains(travelInfo["destinationStationName"]?:"",true)))){
                StationView.displayStation(station.stationName, station.city,station.stationId)
            }
        }
        val stationIds = SearchStationMenuPage.getStationID()
        return Pair(stationIds["startingStationId"]?:-1,stationIds["destinationStationId"]?:-1)
    }
    fun viewAllStations(){
        for(station in Database.stations){
            StationView.displayStation(station.stationName, station.city,station.stationId)
        }
    }
    /*
    fun deleteStationFromSet(stationId: Int): OperationResult{
        for(station in Database.stations){
            if(station.stationId == stationId){
                this.station = station
            }
        }
        return when(Database.stations.remove(station)){
            true -> OperationResult.Success
            false -> OperationResult.Failure
        }
    }
     */
}