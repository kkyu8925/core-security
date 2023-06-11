package com.example.corespringsecurity.security.handler

import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.security.web.DefaultRedirectStrategy
import org.springframework.security.web.RedirectStrategy
import org.springframework.security.web.access.AccessDeniedHandler
import org.springframework.stereotype.Component

@Component
class CustomFormAccessDeniedHandler : AccessDeniedHandler {

    private var errorPage: String = "/denied"
    private val redirectStrategy: RedirectStrategy = DefaultRedirectStrategy()

    override fun handle(
        request: HttpServletRequest,
        response: HttpServletResponse,
        accessDeniedException: org.springframework.security.access.AccessDeniedException
    ) {
        val deniedUrl = errorPage + "?exception=" + accessDeniedException.message
        redirectStrategy.sendRedirect(request, response, deniedUrl)
    }
}
