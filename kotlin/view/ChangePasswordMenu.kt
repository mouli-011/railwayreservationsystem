package view

object ChangePasswordMenu {
    fun getNewPassword(): String = GetInput.getStringInput { println("Enter New Password: ") }
}