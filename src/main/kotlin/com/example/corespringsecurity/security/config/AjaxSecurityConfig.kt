package com.example.corespringsecurity.security.config

import com.example.corespringsecurity.security.filter.AjaxLoginProcessingFilter
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.core.annotation.Order
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.web.SecurityFilterChain
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter

@Order(0)
@Configuration
class AjaxSecurityConfig {

    @Bean
    fun authenticationManager(authenticationConfiguration: AuthenticationConfiguration): AuthenticationManager {
        return authenticationConfiguration.authenticationManager
    }

    @Bean
    fun ajaxSecurityFilterChain(
        http: HttpSecurity,
        authenticationConfiguration: AuthenticationConfiguration
    ): SecurityFilterChain {
        return http.securityMatcher("/api/**")
            .authorizeHttpRequests {
                it.requestMatchers("/api/login").permitAll()
                    .anyRequest().authenticated()
            }.addFilterBefore(
                ajaxLoginProcessingFilter(authenticationConfiguration), UsernamePasswordAuthenticationFilter::class.java
            ).csrf {
                it.disable()
            }.build()
    }

    @Bean
    fun ajaxLoginProcessingFilter(authenticationConfiguration: AuthenticationConfiguration): AjaxLoginProcessingFilter {
        val ajaxLoginProcessingFilter = AjaxLoginProcessingFilter()
        ajaxLoginProcessingFilter.setAuthenticationManager(authenticationManager(authenticationConfiguration))
        return ajaxLoginProcessingFilter
    }
}
