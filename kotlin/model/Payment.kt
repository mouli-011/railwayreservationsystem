package model

import controller.PaymentIdGenerator

data class Payment(val amount: Double){
    val paymentId: Int = PaymentIdGenerator.getPaymentId()
}