package com.example.corespringsecurity.controller.user

import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping

@Controller
class UserController {

    @GetMapping("/users")
    fun createUser(): String {
        return "user/login/register"
    }
}
