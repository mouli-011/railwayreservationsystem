package controller

import database.Database
import model.Admin
import model.Gender
import view.OperationResult
import java.time.LocalDate

class AdminController: PasswordRetriever {
    private lateinit var admin:Admin
    fun addAdminToSet(userName: String,password: String,dateOfBirth: LocalDate,address: String,gender: Gender,userId: String): OperationResult{
        admin = Admin(userName,password,dateOfBirth,address,gender,userId)
        Database.admins.add(admin)
        return OperationResult.Success
    }
    fun addAdminToSet(userName: String,password: String,dateOfBirth: LocalDate,address: String,gender: Gender): OperationResult{
        admin = Admin(userName,password,dateOfBirth,address,gender)
        Database.admins.add(admin)
        return OperationResult.Success
    }

    fun removeAdminFromSet(userId: String): OperationResult{
        for(admin in Database.admins){
            if(admin.userId == userId){
                this.admin = admin
            }
        }
        return when(Database.admins.remove(admin)){
            true -> OperationResult.Success
            false -> OperationResult.Failure
        }
    }
    /*
    fun retrieveAdminFromSet(userId: String): Map<String,String>?{
        for(admin in Database.admins){
            if(admin.userId == userId){
                return mapOf("userName" to admin.userName,"dateOfBirth" to admin.dateOfBirth.toString(),"address" to admin.address,"gender" to admin.gender.toString())
            }
        }
        return null
    }
    */

}