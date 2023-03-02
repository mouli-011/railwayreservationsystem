package controller

import view.OperationResult

interface PasswordChecker {
    fun checkPassword(password: String,passwordToCheck: String): OperationResult = when(password==passwordToCheck){
        true -> OperationResult.Success
        false -> OperationResult.Failure
    }
}