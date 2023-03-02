package view

import model.CoachName
import model.ConstantStrings
import java.time.LocalDate

object BookTicketsMenuPage {
    fun getTrainNumber(availableTrainIds: Set<Int>): Int{
        var trainNumberChosen: Int
        println("\nEnter Train Number From The Above List: ")
        whileLoop@ while(true){
            trainNumberChosen = GetInput.getIntInput {}
            for (trainNumber in availableTrainIds){
                if(trainNumber == trainNumberChosen){
                    break@whileLoop
                }
            }
            println(ConstantStrings.ENTERVALIDINPUT.message)
        }
        return trainNumberChosen
    }
    fun getNoOfTickets(): Int = GetInput.getIntInput { println("Enter Number Of Tickets: ") }

    fun getCoach(coachNames: Map<CoachName,Int>): CoachName{
        println("Select the Coach:")
        coachNames[CoachName.SEATER]?.let {  println("1. ${CoachName.SEATER} ${coachNames[CoachName.SEATER]} seats available") }
        coachNames[CoachName.SLEEPER]?.let { println("2. ${CoachName.SLEEPER} ${coachNames[CoachName.SLEEPER]} seats available") }
        coachNames[CoachName.AC]?.let { println("3. ${CoachName.AC} ${coachNames[CoachName.AC]} seats available") }
        return when(GetInput.getIntInput {  }){
            1 -> CoachName.SEATER
            2 -> CoachName.SLEEPER
            3 -> CoachName.AC
            else -> getCoach(coachNames)
        }
    }
    fun getDateOfTravel(): LocalDate = LocalDate.parse(GetInput.getStringInput { println("Enter date Of Travel: ") })
    fun getPassengerDetails(): Map<String,String>{
        return mapOf(ConstantStrings.PASSENGERNAME.message to GetInput.getStringInput { println("Enter Passenger Name: ") },ConstantStrings.PASSENGERAGE.message to GetInput.getStringInput { println("Enter Passenger Age: ") })
    }
}