package view

object LogInMenuPage {
    fun getLogInDetails(): Map<String,String> = mapOf("userId" to GetInput.getStringInput { println("Enter UserId: ") }, "password" to GetInput.getStringInput { println("Enter password: ") })
}