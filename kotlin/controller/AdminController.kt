package controller

import database.Database
import model.Admin
import model.Gender
import view.OperationResult
import java.time.LocalDate

class AdminController {
    private lateinit var admin:Admin
    fun addAdminToSet(userName: String,password: String,dateOfBirth: LocalDate,address: String,gender: Gender,userId: String): OperationResult{
        admin = Admin(userName,password,dateOfBirth,address,gender,userId)
        return Database.addAdmin(admin)
    }
    fun removeAdminFromSet(userId: String): OperationResult = Database.removeAdmin(userId)
    /*
    fun addAdminToSet(userName: String,password: String,dateOfBirth: LocalDate,address: String,gender: Gender): OperationResult{
        admin = Admin(userName,password,dateOfBirth,address,gender)
        return Database.addAdmin(admin)
    }
    */
}