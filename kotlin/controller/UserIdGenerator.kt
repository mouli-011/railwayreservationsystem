package controller

import model.ConstantStrings
import model.UserRole

object UserIdGenerator {
    private var userIdReference = 0
    fun getUserId(userRole: UserRole): String{
       return when(userRole){
           UserRole.ADMIN -> userIdReference++.toString() + ConstantStrings.A.message
           UserRole.CUSTOMER -> userIdReference++.toString() + ConstantStrings.C.message
       }
    }
}