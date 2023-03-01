package controller

import model.Days
import view.MainMenuPage
import view.OperationResult
import kotlin.system.exitProcess

fun main(){
    while(true){
        when(MainMenuPage.mainMenu()){
            1 -> {
                val signUpController = SignUpController()
                when(signUpController.signUp()){
                    OperationResult.Failure -> OperationResult.Failure.notifyUser("SignUp")
                    OperationResult.Success -> OperationResult.Success.notifyUser("SignUp")
                }
            }
            2 -> {
                val logInController = LogInController()
                logInController.logIn()
            }
            0 -> exitProcess(0)
            else -> println("Enter Valid Input")
        }
    }
}
fun String.toDay(): Days = when(this){
    Days.MONDAY.name -> Days.MONDAY
    Days.TUESDAY.name -> Days.TUESDAY
    Days.WEDNESDAY.name -> Days.WEDNESDAY
    Days.THURSDAY.name -> Days.THURSDAY
    Days.FRIDAY.name -> Days.FRIDAY
    Days.SATURDAY.name -> Days.SATURDAY
    else -> Days.SUNDAY
}