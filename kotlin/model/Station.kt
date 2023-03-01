package model

import controller.StationIdGenerator


data class Station(val stationName:String, val city:String,val distanceFromCapeComorin: Long){
    val stationId: Int = StationIdGenerator.getStationId()
}