package view

import model.ConstantStrings

object LogInMenuPage {
    fun getLogInDetails(): Map<String,String> = mapOf(ConstantStrings.USERID.message to GetInput.getStringInput { println("Enter UserId: ") }, ConstantStrings.PASSWORD.message to GetInput.getStringInput { println("Enter password: ") })
}