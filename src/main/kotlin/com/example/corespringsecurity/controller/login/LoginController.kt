package com.example.corespringsecurity.controller.login

import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.ui.set
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestParam

@Controller
class LoginController {

    @GetMapping("/login")
    fun login(
        @RequestParam(value = "error", required = false) error: String?,
        @RequestParam(value = "exception", required = false) exception: String?,
        model: Model
    ): String {

        if (!error.isNullOrEmpty()) {
            model["error"] = error
        }
        if (!exception.isNullOrEmpty()) {
            model["exception"] = exception
        }

        return "login"
    }

    @GetMapping("/logout")
    fun logout(request: HttpServletRequest, response: HttpServletResponse): String {
        val authentication = SecurityContextHolder.getContext().authentication
        SecurityContextLogoutHandler().logout(request, response, authentication)
        return "redirect:/login"
    }
}
