package controller

import database.Database
import model.ConstantStrings
import view.*

class CustomerOperations: UserOperations{
    fun customerOperations(userId: String){
        CustomerView.displayCustomerName(Database.getCustomer(userId)?.userName?:ConstantStrings.DUMMY.message)
        while (true) {
            DisplayMenuName.displayMenuName("MAIN MENU PAGE")
            when (CustomerOperationsMenu.customerOperationsMainMenu()) {
                1 -> {
                    DisplayMenuName.displayMenuName("CHANGE PASSWORD PAGE")
                    when(changePassword(userId)){
                        OperationResult.Failure -> OperationResult.Failure.notifyUser(ConstantStrings.PASSWORDCHANGE.message)
                        OperationResult.Success -> OperationResult.Success.notifyUser(ConstantStrings.PASSWORDCHANGE.message)
                    }
                    break
                }
                2 -> {
                    DisplayMenuName.displayMenuName("BOOK TICKETS PAGE")
                    val ticketBooker = TicketBooker()
                    when(ticketBooker.bookTickets(userId)){
                        OperationResult.Failure -> OperationResult.Failure.notifyUser(ConstantStrings.TICKETBOOKING.message)
                        OperationResult.Success -> OperationResult.Success.notifyUser(ConstantStrings.TICKETBOOKING.message)
                    }
                }
                3 -> {
                    DisplayMenuName.displayMenuName("VIEW BOOKED TICKETS PAGE")
                    val ticketController = TicketController()
                    ticketController.viewBookedTickets(userId)
                }
                4 -> {
                    DisplayMenuName.displayMenuName("SEARCH TRAIN PAGE")
                    val trainController = TrainController()
                    trainController.searchTrain()
                }
                5 -> {
                    DisplayMenuName.displayMenuName("MY PROFILE")
                    viewProfile(userId)
                }
                0 -> break
                else -> println(ConstantStrings.ENTERVALIDINPUT.message)
            }
        }
    }
    private fun viewProfile(userId: String){
        Database.getCustomer(userId)?.let { customer ->
            CustomerView.displayCustomers(setOf(customer))
        }
    }
    override fun changePassword(userId: String): OperationResult {
        val customerController = CustomerController()
        val customer = Database.getCustomer(userId)
        customer?.let {
            val newPassword = ChangePasswordMenu.getNewPassword()
            return when(customerController.removeCustomerFromSet(userId)){
                OperationResult.Failure -> OperationResult.Failure
                OperationResult.Success -> customerController.addCustomerToSet(customer.userName,newPassword,customer.address,customer.dateOfBirth,customer.gender,customer.userId)
            }
        }
        return OperationResult.Failure
    }

}