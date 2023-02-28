package controller

import database.Database

interface PasswordRetriever {
    fun retrievePasswordFromCustomerSet(userId: String): String?{
        for(customer in Database.customers){
            if(customer.userId == userId){
                return customer.password
            }
        }
        return null
    }
    fun retrievePasswordFromAdminSet(userId: String): String?{
        for(admin in Database.admins){
            if(admin.userId == userId){
                return admin.password
            }
        }
        return null
    }
}