package controller

import database.Database
import model.Passenger
import model.Payment
import model.Ticket
import view.OperationResult
import view.TicketView
import java.time.LocalDateTime

class TicketController {
    private lateinit var ticket: Ticket
    fun addTicketToSet(userId: String, totalTicketsBooked: Int, trainNumber: Int, startingStationId: Int, destinationStationId: Int, paymentDetails: Payment, passengers: List<Passenger>, trainArrivalDateTime: LocalDateTime): OperationResult{
        ticket = Ticket(userId,totalTicketsBooked,passengers,trainNumber,startingStationId,destinationStationId,trainArrivalDateTime,paymentDetails)
        return when(Database.ticketsSold.add(ticket)){
            true -> OperationResult.Success
            false -> OperationResult.Failure
        }
    }
    fun viewBookedTickets(userId: String){
        var ticketsBooked = 0
        for (ticket in Database.ticketsSold) {
            if (ticket.userId == userId) {
                ticketsBooked++
                with(TicketView) {
                    displayTicket(ticket.userId, ticket.totalTicketsBooked, ticket.trainNumber, ticket.startingStationId, ticket.destinationStationId, ticket.trainArrivalDateTime, ticket.ticketId)
                    ticket.passengers.forEach {
                        displayPassengers(it.passengerName, it.passengerAge, it.seatNumber)
                    }
                    displayPayment(ticket.paymentDetails.amount, ticket.paymentDetails.paymentId)
                }
            }
        }
       if(ticketsBooked==0){
            println("No Tickets Booked")
        }
    }
}