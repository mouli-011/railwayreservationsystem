package controller

import model.ConstantStrings
import model.Days
import view.DisplayMenuName
import view.MainMenuPage
import view.OperationResult
import kotlin.system.exitProcess

fun main(){
    while(true){
        DisplayMenuName.displayMenuName("RAILWAY RESERVATION SYSTEM")
        when(MainMenuPage.mainMenu()){
            1 -> {
                val signUpController = SignUpController()
                when(signUpController.signUp()){
                    OperationResult.Failure -> OperationResult.Failure.notifyUser(ConstantStrings.SIGNUP.message)
                    OperationResult.Success -> OperationResult.Success.notifyUser(ConstantStrings.SIGNUP.message)
                }
            }
            2 -> {
                val logInController = LogInController()
                logInController.logIn()
            }
            0 -> exitProcess(0)
            else -> {
                println(ConstantStrings.ENTERVALIDINPUT.message)
            }
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