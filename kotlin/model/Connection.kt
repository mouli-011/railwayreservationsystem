package model

data class Connection(val station: Station,val arrivalDaysTime: DaysTime){
    override fun toString(): String = "Station Id: ${station.stationId}\nStationName: ${station.stationName}\nCity: ${station.city}\nTrain Arriving Time: ${arrivalDaysTime.time}"
}
