package controller

import database.Database
import view.*

class AdminOperations: UserOperations {
    private val adminController = AdminController()
    private val stationController = StationController()
    private val trainController = TrainController()

    fun adminOperations(userId: String){
        while (true) {
            when (AdminOperationsMenu.adminOperationsMainMenu()) {
                0 -> break
                1 -> {
                    when(changePassword(userId)){
                        OperationResult.Failure -> OperationResult.Failure.notifyUser("Password Change ")
                        OperationResult.Success -> OperationResult.Success.notifyUser("Password Change")
                    }
                    break
                }
                2 -> {
                    when(addStationOperation()){
                        OperationResult.Failure -> OperationResult.Failure.notifyUser("Add Station")
                        OperationResult.Success -> OperationResult.Success.notifyUser("Add Station")
                    }
                }
                3 -> {
                    when(addTrainOperation()){
                        OperationResult.Failure -> OperationResult.Failure.notifyUser("Train Addition")
                        OperationResult.Success -> OperationResult.Success.notifyUser("Train Addition")
                    }
                }
                4 -> viewCustomers()
                5 -> viewTicketsSold()
                6 -> stationController.viewAllStations()
                7 -> trainController.viewAllTrains()

            }
        }
    }
    private fun addTrainOperation(): OperationResult{
        val trainName = AddTrainMenuPage.getTrainName()
        stationController.viewAllStations()
        val route = AddTrainMenuPage.getRoute()
        val coachDetails = mutableListOf<Map<String,String>>()
        while(true){
            coachDetails.add(AddTrainMenuPage.getCoachDetails())
            if(AddTrainMenuPage.getNextValue()=="-1")
                break
        }
        return trainController.addTrainToSet(trainName,trainController.createCoach(coachDetails),trainController.createRoute(route))
    }
    private fun addStationOperation(): OperationResult{
        val stationDetails = AddStationMenuPage.getStationDetails()
        return  stationController.addStationToSet(stationDetails["stationName"]?:"",stationDetails["city"]?:"",stationDetails["distanceFromCapeComorin"]?.toLong()?:0L)
    }
    override fun changePassword(userId: String): OperationResult {
        val admin = Database.getAdmin(userId)
        admin?.let {
            val newPassword = ChangePasswordMenu.getNewPassword()
            return when(adminController.removeAdminFromSet(userId)){
                OperationResult.Failure -> OperationResult.Failure
                OperationResult.Success -> adminController.addAdminToSet(admin.userName,newPassword,admin.dateOfBirth,admin.address,admin.gender,admin.userId)
            }
        }
        return OperationResult.Failure
    }
    private fun viewCustomers(){
        val customers = Database.getAllCustomers()
        if(customers.isNotEmpty()){
            CustomerView.displayCustomers(customers)
        }
        else{
            println("No Customers Found")
        }
    }
    private fun viewTicketsSold(){
        val ticketsSold = Database.getAllTicketsSold()
        if(ticketsSold.isNotEmpty()) {
            TicketView.displayTickets(ticketsSold)
        }
        else{
            println("No Tickets Sold")
        }
    }
}