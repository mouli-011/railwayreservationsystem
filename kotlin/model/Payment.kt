package model

import controller.PaymentIdGenerator

data class Payment(val amount: Double){
    private val paymentId: Int = PaymentIdGenerator.getPaymentId()
    override fun toString(): String = "Payment Id: $paymentId\nAmount: $amount"
}