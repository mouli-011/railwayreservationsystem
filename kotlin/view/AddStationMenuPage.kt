package view

import model.ConstantStrings

object AddStationMenuPage {
    fun getStationDetails(): Map<String,String>{
        val stationName = GetInput.getStringInput { println("Enter Station Name: ") }
        val city = GetInput.getStringInput { println("Enter City Name: ") }
        val distanceFromCapeComorin = GetInput.getStringInput { println("Enter Distance from Cape Comorin") }
        return mapOf(ConstantStrings.STATIONNAME.message to stationName,ConstantStrings.CITY.message to city,ConstantStrings.DISTANCEFROMCAPECOMORIN.message to distanceFromCapeComorin)
    }
}