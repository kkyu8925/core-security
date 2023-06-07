package com.example.corespringsecurity.service.impl

import com.example.corespringsecurity.domain.Account
import com.example.corespringsecurity.repository.UserRepository
import com.example.corespringsecurity.service.UserService
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class UserServiceImpl(
    private val userRepository: UserRepository
) : UserService {

    @Transactional
    override fun createUser(account: Account) {
        userRepository.save(account)
    }
}
