package controller

import model.UserRole
import view.LogInMenuPage
import view.OperationResult

class LogInController: PasswordChecker,PasswordRetriever {

    private val adminOperations = AdminOperations()
    private val customerOperations = CustomerOperations()

    fun logIn(){
        val logInDetails = LogInMenuPage.getLogInDetails()
        val userRole = checkUserRole(logInDetails["userId"]?:"")
        userRole?.let {
            fetchUserPassword(it,logInDetails["userId"]?:"")?.let { actualPassword ->
                when(checkPassword(actualPassword,logInDetails["password"]?:"")){
                    OperationResult.Failure -> OperationResult.Failure.notifyUser("Log In")
                    OperationResult.Success -> {
                        OperationResult.Success.notifyUser("Log In")
                        when(it){
                            UserRole.ADMIN -> adminOperations.adminOperations(logInDetails["userId"]?:"")
                            UserRole.CUSTOMER -> customerOperations.customerOperations(logInDetails["userId"]?:"")
                        }
                    }
                }
            }
        }?: OperationResult.Failure.notifyUser("Log In")
    }
    
    private fun checkUserRole(userId: String): UserRole? = when(userId.last().uppercase()){
        "A"-> UserRole.ADMIN
        "C" -> UserRole.CUSTOMER
        else -> null
    }
    private fun fetchUserPassword(userRole: UserRole, userId: String): String? = when(userRole){
        UserRole.ADMIN -> retrievePasswordFromAdminSet(userId)
        UserRole.CUSTOMER -> retrievePasswordFromCustomerSet(userId)
    }

}