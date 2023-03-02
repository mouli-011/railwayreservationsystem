package database

import model.*
import view.OperationResult
import java.time.LocalDate

object Database {

    private val customers = mutableSetOf<Customer>()
    private val admins = mutableSetOf<Admin>()
    private val trains = mutableSetOf<Train>()
    private val stations = mutableSetOf<Station>()
    private val ticketsSold = mutableSetOf<Ticket>()
    init{
        admins.add(Admin("m","m", LocalDate.parse("2001-11-11"),"m",Gender.MALE))
    }

    fun getCustomer(userId: String): Customer? = customers.firstOrNull{ customer -> customer.userId == userId }
    fun getAllCustomers(): Set<Customer> = customers
    fun getAdmin(userId: String): Admin? = admins.firstOrNull { admin -> admin.userId == userId }
    fun getTrain(trainNumber: Int): Train? = trains.firstOrNull { train -> train.trainNumber == trainNumber }
    fun getAllTrains(): Set<Train> = trains
    fun getStation(stationId: Int): Station? = stations.firstOrNull { station -> station.stationId == stationId}
    fun getAllStations(): Set<Station> = stations
    //fun getTicket(ticketId: Int): List<Ticket> = ticketsSold.filter { ticket -> ticket.ticketId == ticketId }
    fun getAllTicketsSold(): Set<Ticket> = ticketsSold
    fun addCustomer(customer: Customer): OperationResult = when(customers.add(customer)){
            true -> OperationResult.Success
            false -> OperationResult.Failure
    }
    fun addAdmin(admin: Admin): OperationResult = when(admins.add(admin)){
            true -> OperationResult.Success
            false -> OperationResult.Failure
    }
    fun addTrain(train: Train): OperationResult = when(trains.add(train)){
            true -> OperationResult.Success
            false -> OperationResult.Failure

    }
    fun addTicket(ticket: Ticket): OperationResult = when(ticketsSold.add(ticket)){
        true -> OperationResult.Success
        false -> OperationResult.Failure
    }
    fun addStation(station: Station): OperationResult = when(stations.add(station)){
        true -> OperationResult.Success
        false -> OperationResult.Failure
    }
    fun removeCustomer(userId: String): OperationResult = when(customers.removeIf { customer -> customer.userId == userId }){
        true -> OperationResult.Success
        false -> OperationResult.Failure
    }
    fun removeTrain(trainNumber: Int): OperationResult = when(trains.removeIf { train -> train.trainNumber == trainNumber }){
        true -> OperationResult.Success
        false -> OperationResult.Failure
    }
    fun removeAdmin(userId: String): OperationResult = when(admins.removeIf { admin -> admin.userId == userId }){
        true -> OperationResult.Success
        false -> OperationResult.Failure
    }
}
