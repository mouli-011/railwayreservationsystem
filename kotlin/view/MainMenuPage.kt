package view

object MainMenuPage {
    fun mainMenu(): Int = GetInput.getIntInput { println("Enter\n1.To Sign Up\n2.To Log In\n0.To Exit") }

}