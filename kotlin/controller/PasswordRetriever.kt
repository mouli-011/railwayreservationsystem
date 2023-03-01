package controller

import database.Database

interface PasswordRetriever {
    fun retrievePasswordFromCustomerSet(userId: String): String? = Database.getCustomer(userId)?.password
    fun retrievePasswordFromAdminSet(userId: String): String? =  Database.getAdmin(userId)?.password
}