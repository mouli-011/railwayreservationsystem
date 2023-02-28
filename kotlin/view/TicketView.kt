package view

import java.time.LocalDateTime

object TicketView {
    fun displayTicket(userId: String,
                      totalTicketsBooked: Int,
                      trainNumber: Int,
                      startingStationId: Int,
                      destinationStationId: Int,
                      trainArrivalDateTime: LocalDateTime,
                      ticketId: Int){
        println("UserId: $userId\nTicketId: $ticketId\nTrain Number: $trainNumber\nStarting Station Id: $startingStationId\nDestination Station Id: $destinationStationId\nTrain Arrival Date And Time: $trainArrivalDateTime\nTotal Tickets Booked: $totalTicketsBooked\n")
    }
    fun displayPassengers(passengerName: String,passengerAge: Short,seatNumber: String){
        println("Passenger Name: $passengerName\nPassenger Age: $passengerAge\nSeat Number: $seatNumber")
    }
    fun displayPayment(amount: Double,paymentId: Int) {
        println("Payment ID: $paymentId\nAmount: $amount")
    }
}