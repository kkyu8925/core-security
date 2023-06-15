package com.example.corespringsecurity.security.provider

import com.example.corespringsecurity.security.service.AccountContext
import com.example.corespringsecurity.security.token.AjaxAuthenticationToken
import org.springframework.security.authentication.AuthenticationProvider
import org.springframework.security.authentication.BadCredentialsException
import org.springframework.security.core.Authentication
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Component
import org.springframework.transaction.annotation.Transactional

@Component
class AjaxAuthenticationProvider(
    private val userDetailsService: UserDetailsService,
    private val passwordEncoder: PasswordEncoder,
) : AuthenticationProvider {

    @Transactional
    override fun authenticate(authentication: Authentication): Authentication {
        val loginId = authentication.name
        val password = authentication.credentials as String
        val accountContext = userDetailsService.loadUserByUsername(loginId) as AccountContext

        if (!passwordEncoder.matches(password, accountContext.password)) {
            throw BadCredentialsException("Invalid password")
        }

        return AjaxAuthenticationToken(accountContext.account, null, accountContext.authorities)
    }

    override fun supports(authentication: Class<*>): Boolean {
        return AjaxAuthenticationToken::class.java.isAssignableFrom(authentication)
    }
}
