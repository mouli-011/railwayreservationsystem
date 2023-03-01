package controller

import database.Database
import model.Customer
import view.ChangePasswordMenu
import view.CustomerOperationsMenu
import view.CustomerView
import view.OperationResult

class CustomerOperations: UserOperations{
    private lateinit var customer:Customer
    private val ticketController = TicketController()
    private val ticketBooker = TicketBooker()
    private val customerController = CustomerController()
    private val trainController = TrainController()
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
                    when(ticketBooker.bookTickets(userId)){
                        OperationResult.Failure -> OperationResult.Failure.notifyUser("Ticket Booking")
                        OperationResult.Success -> OperationResult.Success.notifyUser("Ticket Booking")
                    }
                }
                3 -> ticketController.viewBookedTickets(userId)
                4 -> trainController.searchTrain()
                5 -> viewProfile(userId)
                0 -> break
            }
        }
    }
    private fun viewProfile(userId: String){
        for(customer in Database.customers){
            if(userId == customer.userId){
                CustomerView.displayCustomer(customer.userName,customer.dateOfBirth,customer.address,customer.gender,customer.userId)
            }
        }
    }
    override fun changePassword(userId: String): OperationResult {
        for(customer in Database.customers){
            if(userId == customer.userId){
                val newPassword = ChangePasswordMenu.getNewPassword()
                this.customer = Customer(customer.userName,newPassword,customer.dateOfBirth,customer.address,customer.gender,customer.userId)
            }
        }
        return when(customerController.removeCustomerFromSet(userId)){
            OperationResult.Failure -> OperationResult.Failure
            OperationResult.Success -> customerController.addCustomerToSet(customer.userName,customer.password,customer.address,customer.dateOfBirth.toString(),customer.gender,customer.userId)
        }
    }

}