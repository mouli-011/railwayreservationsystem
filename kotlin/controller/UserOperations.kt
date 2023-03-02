package controller

import view.OperationResult

interface UserOperations {
    fun changePassword(userId: String): OperationResult
}