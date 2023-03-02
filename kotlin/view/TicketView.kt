package view

import model.Passenger
import model.Payment
import model.Ticket

object TicketView {

    fun displayTickets(tickets: Set<Ticket>) {
        tickets.forEach { ticket ->
            with(ticket){
                println()
                println(ticket.toString())
                displayPassengers(passengers)
                displayPayment(paymentDetails)
            }
        }
    }
    private fun displayPassengers(passengers: List<Passenger>){
        passengers.forEach { passenger ->
                println(passenger.toString())
        }
    }
    private fun displayPayment(payment: Payment){
        println(payment.toString())
    }
}