package view

import model.Gender
import java.time.LocalDate

object CustomerView {
    fun displayCustomer(userName: String,dateOfBirth: LocalDate,address: String,gender: Gender,userId: String){
        println("User Id: $userId")
        println("UserName: $userName")
        println("Date Of Birth: $dateOfBirth")
        println("Address: $address")
        println("Gender $gender")
    }
}