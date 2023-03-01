package view

import model.Passenger
import model.Payment
import model.Ticket

object TicketView {

    fun displayTickets(tickets: Set<Ticket>) {
        tickets.forEach { ticket ->
            with(ticket){
                println("UserId: $userId\nTicketId: $ticketId\nTrain Number: $trainNumber\nStarting Station Id: $startingStationId\nDestination Station Id: $destinationStationId\nTrain Arrival Date And Time: $trainArrivalDateTime\nTotal Tickets Booked: $totalTicketsBooked\n")
                displayPassengers(passengers)
                displayPayment(paymentDetails)
            }
        }
    }
    private fun displayPassengers(passengers: List<Passenger>){
        passengers.forEach { passenger ->
            with(passenger){
                println("Passenger Name: $passengerName\nPassenger Age: $passengerAge\nSeat Number: $seatNumber")
            }
        }
    }
    private fun displayPayment(payment: Payment){
        println("Payment ID: ${payment.paymentId}\nAmount: ${payment.amount}")
    }
}