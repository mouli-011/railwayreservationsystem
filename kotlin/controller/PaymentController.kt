package controller

import model.Payment
import view.PaymentViewPage

class PaymentController{
    fun makePayment(amount: Double): Payment?{
        return if(PaymentViewPage.getConfirmation(amount)){
            addPayment(amount)
        } else{
            null
        }
    }
    private fun addPayment(amount: Double): Payment {
        return Payment(amount)
    }
}