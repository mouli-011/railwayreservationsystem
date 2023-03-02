package controller

import model.ConstantStrings
import model.Gender
import view.DisplayMenuName
import view.OperationResult
import view.SignUpMenu
import java.time.LocalDate

class SignUpController {
    fun signUp(): OperationResult{
        val customerController = CustomerController()
        DisplayMenuName.displayMenuName("SIGN UP PAGE")
        val signUpDetails = SignUpMenu.getSignUpDetails()
        return customerController.addCustomerToSet(signUpDetails[ConstantStrings.USERNAME.message]!!,signUpDetails[ConstantStrings.PASSWORD.message]!!,signUpDetails[ConstantStrings.ADDRESS.message]!!, LocalDate.parse(signUpDetails[ConstantStrings.DATEOFBIRTH.message]!!),when(signUpDetails[ConstantStrings.GENDER.message]){
            Gender.MALE.name -> Gender.MALE
            Gender.FEMALE.name -> Gender.FEMALE
            else -> Gender.MALE
        })
    }
}