package controller

object PaymentIdGenerator {
    private var paymentIdReference = 0
    fun getPaymentId(): Int = paymentIdReference++
}