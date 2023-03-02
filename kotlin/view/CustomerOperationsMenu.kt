package view

object CustomerOperationsMenu {
    fun customerOperationsMainMenu(): Int = GetInput.getIntInput { println("Enter\n1.To Change Password\n2.To Book Tickets\n3.To View Booked Tickets\n4.To Search Train\n5.To View Profile\n0.To Log Out") }
}

