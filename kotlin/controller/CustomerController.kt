package controller

import database.Database
import model.Customer
import model.Gender
import view.OperationResult
import java.time.LocalDate

class CustomerController{
    fun addCustomerToSet(userName: String,password: String,address: String,dateOfBirth: LocalDate,gender: Gender): OperationResult{
        val customer = Customer(userName,password,dateOfBirth,address,gender)
        return Database.addCustomer(customer)
        }
    fun addCustomerToSet(userName: String,password: String,address: String,dateOfBirth: LocalDate,gender: Gender,userId: String): OperationResult{
        val customer = Customer(userName,password,dateOfBirth,address,gender,userId)
        return Database.addCustomer(customer)
    }
    fun removeCustomerFromSet(userId: String): OperationResult = Database.removeCustomer(userId)
}