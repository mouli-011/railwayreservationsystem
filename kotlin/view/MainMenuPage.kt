package view

object MainMenuPage {
    fun mainMenu(): Int{
        println("\t\t\tWELCOME TO RAILWAY RESERVATION SYSTEM\n")
        return GetInput.getIntInput { println("Enter\n1.To Sign Up\n2.To Log In\n0.To Exit: ") }
    }

}