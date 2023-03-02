package model

data class Coach(val coachName: CoachName, val noOfCompartments: Int, val totalSeatsInCoach: Int, val availableSeatsInCoach: Int, val farePerKm:Double){
    override fun toString(): String = "Coach Name: $coachName\nNumber Of Compartments: $noOfCompartments\nTotal Seats In Coach: $totalSeatsInCoach\nAvailable Seats In Coach: $availableSeatsInCoach\nFare Per Km: $farePerKm"
}