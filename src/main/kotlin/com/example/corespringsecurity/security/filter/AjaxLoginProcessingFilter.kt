package com.example.corespringsecurity.security.filter

import com.example.corespringsecurity.security.token.AjaxAuthenticationToken
import com.fasterxml.jackson.annotation.JsonCreator
import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.databind.ObjectMapper
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.security.core.Authentication
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter
import org.springframework.security.web.util.matcher.AntPathRequestMatcher

class AjaxLoginProcessingFilter : AbstractAuthenticationProcessingFilter(AntPathRequestMatcher("/api/login")) {

    private val objectMapper = ObjectMapper()

    override fun attemptAuthentication(request: HttpServletRequest, response: HttpServletResponse): Authentication {
        check(isAjax(request)) { "Authentication is not supported" }

        val (username, password) = objectMapper.readValue(request.reader, UsernamePassword::class.java)

        require(username.isNotBlank() && password.isNotBlank()) { "Username or Passoword is empty" }

        val ajaxAuthenticationToken = AjaxAuthenticationToken(username, password)
        return authenticationManager.authenticate(ajaxAuthenticationToken)
    }

    private fun isAjax(request: HttpServletRequest): Boolean {
        return "XMLHttpRequest" == request.getHeader("X-Requested-With")
    }
}

private data class UsernamePassword @JsonCreator constructor(
    @JsonProperty("username") val username: String, @JsonProperty("password") val password: String
)
