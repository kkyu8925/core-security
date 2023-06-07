package com.example.corespringsecurity.domain

import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.Id

@Entity
class Account(
    val username: String,
    val password: String,
    val email: String,
    val age: String,
    val role: String,
) {

    @Id
    @GeneratedValue
    var id: Long = -1
        protected set

}


data class AccountDto(
    val username: String,
    val password: String,
    val email: String,
    val age: String,
    val role: String,
)
