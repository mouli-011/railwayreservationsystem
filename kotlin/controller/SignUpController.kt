package controller

import model.Gender
import view.OperationResult
import view.SignUpMenu
import java.time.LocalDate

class SignUpController {
    fun signUp(): OperationResult{
        val customerController = CustomerController()
        val signUpDetails = SignUpMenu.getSignUpDetails()
        return customerController.addCustomerToSet(signUpDetails["userName"]!!,signUpDetails["password"]!!,signUpDetails["address"]!!, LocalDate.parse(signUpDetails["dateOfBirth"]!!),when(signUpDetails["gender"]){
            "Male" -> Gender.MALE
            "Female" -> Gender.FEMALE
            else -> Gender.MALE
        })
    }
}