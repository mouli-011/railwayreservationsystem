package model

import controller.UserIdGenerator
import java.time.LocalDate

data class Admin(
    override val userName: String,
    override val password: String,
    override val dateOfBirth: LocalDate,
    override val address: String,
    override val gender: Gender,
    override val userId: String = UserIdGenerator.getUserId(UserRole.ADMIN)
    ): User(){
    override fun toString(): String = "UserName: $userName\nuserId: $userId\nDate Of Birth: $dateOfBirth\nAddress: $address\nGender ${gender.name}"
    }
