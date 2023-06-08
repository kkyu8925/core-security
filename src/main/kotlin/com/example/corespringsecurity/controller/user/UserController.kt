package com.example.corespringsecurity.controller.user

import com.example.corespringsecurity.domain.Account
import com.example.corespringsecurity.domain.AccountDto
import com.example.corespringsecurity.service.UserService
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping

@Controller
class UserController(
    private val passwordEncoder: PasswordEncoder, private val userService: UserService
) {

    @GetMapping("/mypage")
    fun myPage(): String {
        return "user/mypage"
    }

    @GetMapping("/users")
    fun createUser(): String {
        return "user/login/register"
    }

    @PostMapping("/users")
    fun createUser(accountDto: AccountDto): String {
        val account = Account(
            username = accountDto.username,
            password = passwordEncoder.encode(accountDto.password),
            email = accountDto.email,
            age = accountDto.age,
            role = accountDto.role
        )
        userService.createUser(account)
        return "redirect:/"
    }
}
