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
    ): User()
