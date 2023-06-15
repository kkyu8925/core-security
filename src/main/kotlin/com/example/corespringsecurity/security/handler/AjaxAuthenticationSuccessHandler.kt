package com.example.corespringsecurity.security.handler

import com.example.corespringsecurity.domain.Account
import com.fasterxml.jackson.databind.ObjectMapper
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.security.core.Authentication
import org.springframework.security.web.authentication.AuthenticationSuccessHandler

class AjaxAuthenticationSuccessHandler : AuthenticationSuccessHandler {

    private val mapper = ObjectMapper()

    override fun onAuthenticationSuccess(
        request: HttpServletRequest, response: HttpServletResponse, authentication: Authentication
    ) {
        val account = authentication.principal as Account

        response.status = HttpStatus.OK.value()
        response.contentType = MediaType.APPLICATION_JSON_VALUE

        mapper.writeValue(response.writer, account)
    }
}
