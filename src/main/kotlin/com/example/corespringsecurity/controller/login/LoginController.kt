package com.example.corespringsecurity.controller.login

import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping

@Controller
class LoginController {

    @GetMapping("/login")
    fun login(): String {
        return "user/login/login"
    }

    @GetMapping("/logout")
    fun logout(request: HttpServletRequest, response: HttpServletResponse): String {
        val authentication = SecurityContextHolder.getContext().authentication
        SecurityContextLogoutHandler().logout(request, response, authentication)
        return "redirect:/login"
    }
}
