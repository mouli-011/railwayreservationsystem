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
    private val ticketId: Int = TicketIdGenerator.getTicketId()
    override fun toString(): String = "UserId: $userId\nTicket Id: $ticketId\nTrain Number: $trainNumber\nTotal Tickets Booked: $totalTicketsBooked\nStarting Station Id: $startingStationId\nDestination Station Id: $destinationStationId\nTrain Arrival Date And Time: $trainArrivalDateTime"
}