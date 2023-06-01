package com.example.corespringsecurity.security.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.core.userdetails.User
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.crypto.factory.PasswordEncoderFactories
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.security.provisioning.InMemoryUserDetailsManager
import org.springframework.security.web.SecurityFilterChain

@Configuration
@EnableWebSecurity
class SecurityConfig {

    @Bean
    fun userDetailsService(): UserDetailsService {
        val password = passwordEncoder().encode("1234")

        val user = User
            .withUsername("user")
            .password(password)
            .roles("USER")
            .build()

        val sys = User
            .withUsername("manager")
            .password(password)
            .roles("MANAGER")
            .build()

        val admin = User
            .withUsername("admin")
            .password(password)
            .roles("ADMIN")
            .build()

        return InMemoryUserDetailsManager(user, sys, admin)
    }

    @Bean
    fun passwordEncoder(): PasswordEncoder {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder()
    }

    @Bean
    fun securityFilterChain(http: HttpSecurity): SecurityFilterChain {
        return http.authorizeHttpRequests {
            it
                .requestMatchers("/").permitAll()
                .requestMatchers("/mypage").hasRole("USER")
                .requestMatchers("/messages").hasRole("MANAGER")
                .requestMatchers("/config").hasRole("ADMIN")
                .anyRequest().authenticated()
        }.formLogin {
            it.failureForwardUrl("/login")
        }.build()
    }
}
