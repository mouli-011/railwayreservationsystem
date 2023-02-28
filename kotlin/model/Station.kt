package model

import controller.StationIdGenerator

data class Station(val stationName:String,
                   val city:String){
    val stationId: Int = StationIdGenerator.getStationId()
}