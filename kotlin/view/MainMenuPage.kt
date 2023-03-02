package view

object MainMenuPage {
    fun mainMenu(): Int{
        println("\n\t\t\tWELCOME TO RAILWAY RESERVATION SYSTEM\n")
        return GetInput.getIntInput { println("\nEnter\n1.To Sign Up\n2.To Log In\n0.To Exit: ") }
    }

}