package com.example.corespringsecurity.domain.dto

data class AccountDto(
    val id: String,
    val username: String,
    val email: String,
    val age: Int,
    val password: String,
    val roles: List<String>,
)




