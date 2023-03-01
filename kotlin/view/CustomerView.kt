package view

import model.Customer

object CustomerView {
    fun displayCustomers(customers: Set<Customer>){
        customers.forEach { customer ->
            with(customer){
                println("User Id: $userId")
                println("UserName: $userName")
                println("Date Of Birth: $dateOfBirth")
                println("Address: $address")
                println("Gender: $gender")
            }
        }
    }
}