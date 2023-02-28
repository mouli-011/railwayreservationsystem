package controller

import model.Gender
import view.OperationResult
import view.SignUpMenu

class SignUpController {
    private val customerController = CustomerController()
    fun signUp(): OperationResult{
        val signUpDetails = SignUpMenu.getSignUpDetails()
        return customerController.addCustomerToSet(signUpDetails["userName"]!!,signUpDetails["password"]!!,signUpDetails["address"]!!,signUpDetails["dateOfBirth"]!!,when(signUpDetails["gender"]){
            "Male" -> Gender.MALE
            "Female" -> Gender.FEMALE
            else -> Gender.MALE
        })
    }
}