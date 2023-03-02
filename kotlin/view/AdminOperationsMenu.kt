package view

object AdminOperationsMenu {
    fun adminOperationsMainMenu(): Int = GetInput.getIntInput {
        println("Enter\n1.To Change Password\n2.To Add Station\n3.To Add Train\n4.To View Customers\n5.To View Sold Tickets\n6.To View Stations\n7.To View Trains\n0.Log Out")
    }
    fun displayAdminName(userName: String){
        println("\nWelcome $userName")
    }
}