package model

import controller.TicketIdGenerator
import java.time.LocalDateTime

data class Ticket(val userId: String,
                  val totalTicketsBooked: Int,
                  val passengers: List<Passenger>,
                  val trainNumber: Int,
                  val startingStationId: Int,
                  val destinationStationId: Int,
                  val trainArrivalDateTime: LocalDateTime,
                  val paymentDetails: Payment){
    val ticketId: Int = TicketIdGenerator.getTicketId()
}