package controller

object StationIdGenerator {
    private var stationIdReference = 0
    fun getStationId(): Int = stationIdReference++
}