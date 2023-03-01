package view

import model.Customer

object CustomerView {
    fun displayCustomers(customers: Set<Customer>){
        customers.forEach { customer ->
                println(customer.toString())
        }
    }
}