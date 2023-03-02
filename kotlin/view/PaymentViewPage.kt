package view

object PaymentViewPage {
    fun getConfirmation(amount: Double): Boolean{
        println("Your fare is $amount")
        println("Press 1. To Make Payment\n2.To Cancel")
        return when(GetInput.getIntInput {  }){
            1 -> true
            else -> false
        }
    }
}