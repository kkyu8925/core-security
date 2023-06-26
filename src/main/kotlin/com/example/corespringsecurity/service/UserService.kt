package com.example.corespringsecurity.service

import com.example.corespringsecurity.domain.dto.AccountDto
import com.example.corespringsecurity.domain.entity.Account

interface UserService {

    fun createUser(account: Account)

    fun modifyUser(accountDto: AccountDto)

    fun getUsers(): List<Account>

    fun getUser(id: Long): AccountDto

    fun deleteUser(id: Long)
}
