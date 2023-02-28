package view

object SearchStationMenuPage {
    fun getStationInfo(): Map<String,String>{
        return mapOf("startingStationName" to GetInput.getStringInput { println("Enter Starting station: ") },"destinationStationName" to GetInput.getStringInput { println("Enter Destination station: ") })
    }
    fun getStationID(): Map<String,Int>{
        return mapOf("startingStationId" to GetInput.getIntInput { println("Refer Above Table For Station IDs\nEnter Starting Station ID: ") },"destinationStationId" to GetInput.getIntInput { println("Enter Destination station Id: ") })
    }
}