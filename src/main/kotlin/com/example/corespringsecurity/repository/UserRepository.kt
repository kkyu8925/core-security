package com.example.corespringsecurity.repository

import com.example.corespringsecurity.domain.Account
import org.springframework.data.jpa.repository.JpaRepository

interface UserRepository : JpaRepository<Account, Long> {

    fun findByUsername(username: String): Account?
}
