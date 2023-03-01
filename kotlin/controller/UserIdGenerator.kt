package controller

import model.UserRole

object UserIdGenerator {
    private var userIdReference = 0
    fun getUserId(userRole: UserRole): String{
       return when(userRole){
           UserRole.ADMIN ->"${userIdReference++}" + "A"
           UserRole.CUSTOMER ->"${userIdReference++}" + "C"
       }
    }
}