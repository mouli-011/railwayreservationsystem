package controller

import database.Database
import view.ChangePasswordMenu
import view.CustomerOperationsMenu
import view.CustomerView
import view.OperationResult

class CustomerOperations: UserOperations{
    fun customerOperations(userId: String){
        while (true) {
            when (CustomerOperationsMenu.customerOperationsMainMenu()) {
                1 -> {
                    when(changePassword(userId)){
                        OperationResult.Failure -> OperationResult.Failure.notifyUser("Password Change")
                        OperationResult.Success -> OperationResult.Success.notifyUser("Password Change")
                    }
                    break
                }
                2 -> {
                    val ticketBooker = TicketBooker()
                    when(ticketBooker.bookTickets(userId)){
                        OperationResult.Failure -> OperationResult.Failure.notifyUser("Ticket Booking")
                        OperationResult.Success -> OperationResult.Success.notifyUser("Ticket Booking")
                    }
                }
                3 -> {
                    val ticketController = TicketController()
                    ticketController.viewBookedTickets(userId)
                }
                4 -> {
                    val trainController = TrainController()
                    trainController.searchTrain()
                }
                5 -> viewProfile(userId)
                0 -> break
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