package com.example.corespringsecurity.security.handler

import com.fasterxml.jackson.databind.ObjectMapper
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.security.authentication.BadCredentialsException
import org.springframework.security.authentication.CredentialsExpiredException
import org.springframework.security.authentication.DisabledException
import org.springframework.security.core.AuthenticationException
import org.springframework.security.web.authentication.AuthenticationFailureHandler
import org.springframework.stereotype.Component

class AjaxAuthenticationFailureHandler : AuthenticationFailureHandler {

    private val mapper = ObjectMapper()

    override fun onAuthenticationFailure(
        request: HttpServletRequest,
        response: HttpServletResponse,
        exception: AuthenticationException
    ) {
        response.status = HttpStatus.UNAUTHORIZED.value()
        response.contentType = MediaType.APPLICATION_JSON_VALUE

        val errorMessage = when (exception) {
            is BadCredentialsException -> "Invalid Username or Password"
            is DisabledException -> "Locked"
            is CredentialsExpiredException -> "Expired password"
            else -> "Invalid Username or Password"
        }

        mapper.writeValue(response.writer, errorMessage)
    }
}
