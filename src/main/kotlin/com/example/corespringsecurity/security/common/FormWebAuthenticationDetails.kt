package com.example.corespringsecurity.security.common

import jakarta.servlet.http.HttpServletRequest
import org.springframework.security.web.authentication.WebAuthenticationDetails

class FormWebAuthenticationDetails(
    request: HttpServletRequest
) : WebAuthenticationDetails(request) {
    val secretKey: String = request.getParameter("secret_key")
}
