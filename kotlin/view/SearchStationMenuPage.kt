package view

import model.ConstantStrings

object SearchStationMenuPage {
    fun getStationInfo(): Map<String,String>{
        return mapOf(ConstantStrings.STARTINGSTATIONNAME.message to GetInput.getStringInput { println("Enter Starting station: ") },ConstantStrings.DESTINATIONSTATIONNAME.message to GetInput.getStringInput { println("Enter Destination station: ") })
    }
    fun getStationID(): Map<String,Int>{
        return mapOf(ConstantStrings.STARTINGSTATIONID.message to GetInput.getIntInput { println("Refer Above Table For Station IDs\nEnter Starting Station ID: ") },ConstantStrings.DESTINATIONSTATIONID.message to GetInput.getIntInput { println("Enter Destination station Id: ") })
    }
}