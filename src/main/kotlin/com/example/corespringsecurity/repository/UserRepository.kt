package com.example.corespringsecurity.repository

import com.example.corespringsecurity.domain.entity.Account
import org.springframework.data.jpa.repository.JpaRepository
import java.util.*

interface UserRepository : JpaRepository<Account, Long> {

    fun findByUsername(username: String): Optional<Account>

    fun countByUsername(username: String): Int
}
