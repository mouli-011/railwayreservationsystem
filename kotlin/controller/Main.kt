package controller

import model.Days
import model.Gender
import view.MainMenuPage
import view.OperationResult
import java.time.LocalDate
import kotlin.system.exitProcess

fun main(){
    val signUpController = SignUpController()
    val logInController = LogInController()
    val adminController = AdminController()
    adminController.addAdminToSet("mouli","mouli", LocalDate.parse("2001-11-11"),"Pondicherry",Gender.MALE)
    while(true){
        when(MainMenuPage.mainMenu()){
            1 -> {
                when(signUpController.signUp()){
                    OperationResult.Failure -> OperationResult.Failure.notifyUser("SignUp")
                    OperationResult.Success -> OperationResult.Success.notifyUser("SignUp")
                }
            }
            2 -> logInController.logIn()
            0 -> exitProcess(0)
            else -> println("Enter Valid Input")
        }
    }
}
fun String.toDay(): Days = when(this){
    "MONDAY" -> Days.MONDAY
    "TUESDAY" -> Days.TUESDAY
    "WEDNESDAY" -> Days.WEDNESDAY
    "THURSDAY" -> Days.THURSDAY
    "FRIDAY" -> Days.FRIDAY
    "SATURDAY" -> Days.SATURDAY
    else -> Days.SUNDAY
}