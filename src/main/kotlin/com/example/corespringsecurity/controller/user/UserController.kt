package com.example.corespringsecurity.controller.user

import com.example.corespringsecurity.domain.AccountDto
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping

@Controller
class UserController {

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


        return "redirect:/"
    }
}
