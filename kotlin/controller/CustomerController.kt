package controller

import database.Database
import model.Customer
import model.Gender
import view.OperationResult
import java.time.LocalDate

class CustomerController: PasswordRetriever{
    private lateinit var customer:Customer
    fun addCustomerToSet(userName: String,password: String,address: String,dateOfBirth: String,gender: Gender): OperationResult{
        customer = Customer(userName,password, LocalDate.parse(dateOfBirth),address,gender)
        return when(Database.customers.add(customer)){
            true -> OperationResult.Success
            false -> OperationResult.Failure
        }
    }
    fun addCustomerToSet(userName: String,password: String,address: String,dateOfBirth: String,gender: Gender,userId: String): OperationResult{
        customer = Customer(userName,password, LocalDate.parse(dateOfBirth),address,gender,userId)
        return when(Database.customers.add(customer)){
            true -> OperationResult.Success
            false -> OperationResult.Failure
        }
    }
    fun removeCustomerFromSet(userId: String): OperationResult{
        for(customer in Database.customers){
            if(customer.userId == userId){
                this.customer = customer
            }
        }
        return when(Database.customers.remove(customer)){
            true -> OperationResult.Success
            false -> OperationResult.Failure
        }
    }
}