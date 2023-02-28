package controller

import model.UserRole

interface CheckUserRole {
    fun checkUserRole(userId: String): UserRole? = when(userId.last().uppercase()){
        "A"-> UserRole.ADMIN
        "C" -> UserRole.CUSTOMER
        else -> null
    }
}