package view

import model.Customer

object CustomerView {
    fun displayCustomers(customers: Set<Customer>){
        customers.forEach { customer ->
            println()
            println(customer.toString())
        }
    }
    fun displayCustomerName(userName: String){
        println("\nWelcome $userName")
    }
}