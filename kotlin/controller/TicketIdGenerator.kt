package controller

object TicketIdGenerator {
    private var ticketIdReference = 0
    fun getTicketId(): Int = ticketIdReference++
}