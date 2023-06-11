package com.example.corespringsecurity.security.common

import jakarta.servlet.http.HttpServletRequest
import org.springframework.security.authentication.AuthenticationDetailsSource
import org.springframework.security.web.authentication.WebAuthenticationDetails
import org.springframework.stereotype.Component

@Component
class FormWebAuthenticationDetailsSource : AuthenticationDetailsSource<HttpServletRequest, WebAuthenticationDetails> {

    override fun buildDetails(request: HttpServletRequest): WebAuthenticationDetails {
        return FormWebAuthenticationDetails(request)
    }
}
