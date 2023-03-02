package view

object MainMenuPage {
    fun mainMenu(): Int{
        return GetInput.getIntInput { println("\nEnter\n1.To Sign Up\n2.To Log In\n0.To Exit: ") }
    }

}