package model

import java.time.LocalDate

sealed class User{
    abstract val userId: String
    abstract val userName: String
    abstract val password: String
    abstract val dateOfBirth: LocalDate
    abstract val address: String
    abstract val gender: Gender
}
