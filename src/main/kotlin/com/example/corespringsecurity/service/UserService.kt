package com.example.corespringsecurity.service

import com.example.corespringsecurity.domain.Account

interface UserService {

    fun createUser(account: Account)
}
