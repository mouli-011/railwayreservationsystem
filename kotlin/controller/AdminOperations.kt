package controller

import database.Database
import model.Admin
import view.*

class AdminOperations: UserOperations {
    private lateinit var admin: Admin
    private val adminController = AdminController()
    private val stationController = StationController()
    private val trainController = TrainController()

    fun adminOperations(userId: String){
        while (true) {
            when (AdminOperationsMenu.adminOperationsMainMenu()) {
                0 -> break
                1 -> {
                    changePassword(userId)
                    break
                }
                2 -> addStationOperation()
                3 -> addTrainOperation()
                4 -> viewCustomers()
                5 -> viewTicketsSold()
                6 -> stationController.viewAllStations()
                7 -> trainController.viewAllTrains()

            }
        }
    }
    private fun addTrainOperation(){
        val trainName = AddTrainMenuPage.getTrainName()
        stationController.viewAllStations()
        val route = AddTrainMenuPage.getRoute()
        val coachDetails = mutableListOf<Map<String,String>>()
        while(true){
            coachDetails.add(AddTrainMenuPage.getCoachDetails())
            if(AddTrainMenuPage.getNextValue()=="-1")
                break
        }
        trainController.addTrainToSet(trainName,trainController.createCoach(coachDetails),trainController.createRoute(route))
    }
    private fun addStationOperation(){
        val stationDetails = AddStationMenuPage.getStationDetails()
        stationController.addStationToSet(stationDetails["stationName"]?:"",stationDetails["city"]?:"")
    }
    override fun changePassword(userId: String): OperationResult {
        for(admin in Database.admins){
            if(userId == admin.userId){
                val newPassword = ChangePasswordMenu.getNewPassword()
                this.admin = Admin(admin.userName,newPassword,admin.dateOfBirth,admin.address,admin.gender,admin.userId)
            }
        }
        return when(adminController.removeAdminFromSet(userId)){
            OperationResult.Failure -> OperationResult.Failure
            OperationResult.Success -> adminController.addAdminToSet(admin.userName,admin.password,admin.dateOfBirth,admin.address,admin.gender,admin.userId)
        }
    }
    private fun viewCustomers(){
        for(customer in Database.customers){
            CustomerView.displayCustomer(customer.userName,customer.dateOfBirth,customer.address,customer.gender,customer.userId)
        }
    }
    private fun viewTicketsSold(){
        for(ticket in Database.ticketsSold){
            with(TicketView){
                displayTicket(ticket.userId,ticket.totalTicketsBooked,ticket.trainNumber,ticket.startingStationId,ticket.destinationStationId,ticket.trainArrivalDateTime,ticket.ticketId)
                ticket.passengers.forEach { displayPassengers(it.passengerName,it.passengerAge,it.seatNumber) }
                displayPayment(ticket.paymentDetails.amount,ticket.paymentDetails.paymentId)
            }
        }
    }
}