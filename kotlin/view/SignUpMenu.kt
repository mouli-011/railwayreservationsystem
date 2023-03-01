package view

object SignUpMenu {
    fun getSignUpDetails(): Map<String,String>{
        val userName = GetInput.getStringInput { println("Enter Name: ") }
        val dateOfBirth = GetInput.getStringInput { println("Enter Date Of Birth: ") }
        val address = GetInput.getStringInput { println("Enter Address: ") }
        val password = GetInput.getStringInput{ println("Enter Password: ") }
        val gender = GetInput.getGender()
        return mapOf("userName" to userName,"dateOfBirth" to dateOfBirth,"address" to address,"password" to password,"gender" to gender)
    }
}