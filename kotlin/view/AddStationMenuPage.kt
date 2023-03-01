package view

object AddStationMenuPage {
    fun getStationDetails(): Map<String,String>{
        val stationName = GetInput.getStringInput { println("Enter Station Name: ") }
        val city = GetInput.getStringInput { println("Enter City Name: ") }
        val distanceFromCapeComorin = GetInput.getStringInput { println("Enter Distance from Cape Comorin") }
        return mapOf("stationName" to stationName,"city" to city,"distanceFromCapeComorin" to distanceFromCapeComorin)
    }
}