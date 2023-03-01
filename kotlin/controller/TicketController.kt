package controller

import database.Database
import model.Passenger
import model.Payment
import model.Ticket
import view.OperationResult
import view.TicketView
import java.time.LocalDateTime

class TicketController {
    fun addTicketToSet(userId: String, totalTicketsBooked: Int, trainNumber: Int, startingStationId: Int, destinationStationId: Int, paymentDetails: Payment, passengers: List<Passenger>, trainArrivalDateTime: LocalDateTime): OperationResult{
        val ticket = Ticket(userId,totalTicketsBooked,passengers,trainNumber,startingStationId,destinationStationId,trainArrivalDateTime,paymentDetails)
        return Database.addTicket(ticket)
    }
    fun viewBookedTickets(userId: String){
        val ticketsBooked = Database.getAllTicketsSold().filter{ it.userId == userId }.toSet()
        if(ticketsBooked.isNotEmpty()) {
            TicketView.displayTickets(ticketsBooked)
        }
        else{
            println("No Tickets Booked")
        }
    }
}