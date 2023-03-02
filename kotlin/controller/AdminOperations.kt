package controller

import database.Database
import model.ConstantStrings
import view.*

class AdminOperations: UserOperations {

    fun adminOperations(userId: String){
        while (true) {
            when (AdminOperationsMenu.adminOperationsMainMenu()) {
                0 -> break
                1 -> {
                    when(changePassword(userId)){
                        OperationResult.Failure -> OperationResult.Failure.notifyUser(ConstantStrings.PASSWORDCHANGE.message)
                        OperationResult.Success -> OperationResult.Success.notifyUser(ConstantStrings.PASSWORDCHANGE.message)
                    }
                    break
                }
                2 -> {
                    when(addStationOperation()){
                        OperationResult.Failure -> OperationResult.Failure.notifyUser(ConstantStrings.ADDSTATION.message)
                        OperationResult.Success -> OperationResult.Success.notifyUser(ConstantStrings.ADDSTATION.message)
                    }
                }
                3 -> {
                    when(addTrainOperation()){
                        OperationResult.Failure -> OperationResult.Failure.notifyUser(ConstantStrings.TRAINADDITION.message)
                        OperationResult.Success -> OperationResult.Success.notifyUser(ConstantStrings.TRAINADDITION.message)
                    }
                }
                4 -> viewCustomers()
                5 -> viewTicketsSold()
                6 -> {
                    val stationController = StationController()
                    stationController.viewAllStations()
                }
                7 -> {
                    val trainController = TrainController()
                    trainController.viewAllTrains()
                }
                else -> println(ConstantStrings.ENTERVALIDINPUT.message)
            }
        }
    }
    private fun addTrainOperation(): OperationResult{
        val stationController = StationController()
        val trainController = TrainController()
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
        val stationController = StationController()
        val stationDetails = AddStationMenuPage.getStationDetails()
        return  stationController.addStationToSet(stationDetails[ConstantStrings.STATIONNAME.message]?:ConstantStrings.DUMMY.message,stationDetails[ConstantStrings.CITY.message]?:ConstantStrings.DUMMY.message,stationDetails[ConstantStrings.DISTANCEFROMCAPECOMORIN.message]?.toLong()?:0L)
    }
    override fun changePassword(userId: String): OperationResult {
        val adminController = AdminController()
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