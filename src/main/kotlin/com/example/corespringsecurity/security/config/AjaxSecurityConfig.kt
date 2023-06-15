package com.example.corespringsecurity.security.config

import com.example.corespringsecurity.security.common.AjaxLoginAuthenticationEntryPoint
import com.example.corespringsecurity.security.filter.AjaxLoginProcessingFilter
import com.example.corespringsecurity.security.handler.AjaxAccessDeniedHandler
import com.example.corespringsecurity.security.handler.AjaxAuthenticationFailureHandler
import com.example.corespringsecurity.security.handler.AjaxAuthenticationSuccessHandler
import com.example.corespringsecurity.security.provider.AjaxAuthenticationProvider
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.core.annotation.Order
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.web.SecurityFilterChain
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter

@Order(0)
@Configuration
class AjaxSecurityConfig(
    private val ajaxAuthenticationProvider: AjaxAuthenticationProvider,
) {

    @Bean
    fun authenticationManager(http: HttpSecurity): AuthenticationManager {
        val authenticationManagerBuilder = http.getSharedObject(AuthenticationManagerBuilder::class.java)
        authenticationManagerBuilder.authenticationProvider(ajaxAuthenticationProvider)
        return authenticationManagerBuilder.build()
    }

    @Bean
    fun ajaxSecurityFilterChain(http: HttpSecurity): SecurityFilterChain {
        return http.securityMatcher("/api/**").authorizeHttpRequests {
            it
                .requestMatchers("/api/messages").hasRole("USER")
                .requestMatchers("/api/login").permitAll()
                .anyRequest().authenticated()
        }.addFilterBefore(
            ajaxLoginProcessingFilter(http), UsernamePasswordAuthenticationFilter::class.java
        ).csrf {
            it.disable()
        }.exceptionHandling {
            it.accessDeniedHandler(AjaxAccessDeniedHandler())
                .authenticationEntryPoint(AjaxLoginAuthenticationEntryPoint())
        }.build()
    }

    @Bean
    fun ajaxLoginProcessingFilter(http: HttpSecurity): AjaxLoginProcessingFilter {
        val ajaxLoginProcessingFilter = AjaxLoginProcessingFilter()
        ajaxLoginProcessingFilter.setAuthenticationManager(authenticationManager(http))
        ajaxLoginProcessingFilter.setAuthenticationSuccessHandler(AjaxAuthenticationSuccessHandler())
        ajaxLoginProcessingFilter.setAuthenticationFailureHandler(AjaxAuthenticationFailureHandler())
        return ajaxLoginProcessingFilter
    }
}
