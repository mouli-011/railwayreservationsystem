package view

import model.ConstantStrings

object SignUpMenu {
    fun getSignUpDetails(): Map<String,String>{
        val userName = GetInput.getStringInput { println("Enter Name: ") }
        val dateOfBirth = GetInput.getStringInput { println("Enter Date Of Birth: ") }
        val address = GetInput.getStringInput { println("Enter Address: ") }
        val password = GetInput.getStringInput{ println("Enter Password: ") }
        val gender = GetInput.getGender()
        return mapOf(ConstantStrings.USERNAME.message to userName,ConstantStrings.DATEOFBIRTH.message to dateOfBirth,ConstantStrings.ADDRESS.message to address,ConstantStrings.PASSWORD.message to password,ConstantStrings.GENDER.message to gender)
    }
}