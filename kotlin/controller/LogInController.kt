package controller

import model.ConstantStrings
import model.UserRole
import view.DisplayMenuName
import view.LogInMenuPage
import view.OperationResult

class LogInController: PasswordChecker,PasswordRetriever {
    fun logIn(){
        DisplayMenuName.displayMenuName("LOG IN PAGE")
        val logInDetails = LogInMenuPage.getLogInDetails()
        val userRole = checkUserRole(logInDetails[ConstantStrings.USERID.message]?:ConstantStrings.DUMMY.message)
        userRole?.let {
            fetchUserPassword(it,logInDetails[ConstantStrings.USERID.message]?:ConstantStrings.DUMMY.message)?.let { actualPassword ->
                when(checkPassword(actualPassword,logInDetails[ConstantStrings.PASSWORD.message]?:ConstantStrings.DUMMY.message)){
                    OperationResult.Failure -> OperationResult.Failure.notifyUser(ConstantStrings.LOGIN.message)
                    OperationResult.Success -> {
                        OperationResult.Success.notifyUser(ConstantStrings.LOGIN.message)
                        when(it){
                            UserRole.ADMIN -> {
                                val adminOperations = AdminOperations()
                                adminOperations.adminOperations(logInDetails[ConstantStrings.USERID.message]?:ConstantStrings.DUMMY.message)
                            }
                            UserRole.CUSTOMER -> {
                                val customerOperations = CustomerOperations()
                                customerOperations.customerOperations(logInDetails[ConstantStrings.USERID.message]?:ConstantStrings.DUMMY.message)
                            }
                        }
                    }
                }
            }
        }?: OperationResult.Failure.notifyUser(ConstantStrings.LOGIN.message)
    }
    
    private fun checkUserRole(userId: String): UserRole? = when(userId.last().uppercase()){
        ConstantStrings.A.message-> UserRole.ADMIN
        ConstantStrings.C.message -> UserRole.CUSTOMER
        else -> null
    }
    private fun fetchUserPassword(userRole: UserRole, userId: String): String? = when(userRole){
        UserRole.ADMIN -> retrievePasswordFromAdminSet(userId)
        UserRole.CUSTOMER -> retrievePasswordFromCustomerSet(userId)
    }

}