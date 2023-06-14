package com.example.corespringsecurity.security.config

import com.example.corespringsecurity.security.common.CustomFormWebAuthenticationDetailsSource
import com.example.corespringsecurity.security.handler.CustomFormAccessDeniedHandler
import com.example.corespringsecurity.security.handler.CustomFormAuthenticationFailureHandler
import com.example.corespringsecurity.security.handler.CustomFormAuthenticationSuccessHandler
import org.springframework.boot.autoconfigure.security.servlet.PathRequest
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.core.annotation.Order
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer
import org.springframework.security.core.userdetails.User
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.crypto.factory.PasswordEncoderFactories
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.security.provisioning.InMemoryUserDetailsManager
import org.springframework.security.web.SecurityFilterChain

@Order(1)
@Configuration
@EnableWebSecurity
class SecurityConfig(
    private val customFormWebAuthenticationDetailsSource: CustomFormWebAuthenticationDetailsSource,
    private val customFormAuthenticationSuccessHandler: CustomFormAuthenticationSuccessHandler,
    private val customFormAuthenticationFailureHandler: CustomFormAuthenticationFailureHandler,
    private val customFormAccessDeniedHandler: CustomFormAccessDeniedHandler
) {

    @Bean
    fun userDetailsService(): UserDetailsService {
        val password = passwordEncoder().encode("1111")

        val user = User.withUsername("user").password(password).roles("USER").build()
        val sys = User.withUsername("manager").password(password).roles("MANAGER", "USER").build()
        val admin = User.withUsername("admin").password(password).roles("ADMIN", "USER", "MANAGER").build()

        return InMemoryUserDetailsManager(user, sys, admin)
    }

    @Bean
    fun passwordEncoder(): PasswordEncoder {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder()
    }

    @Bean
    fun webSecurityCustomizer(): WebSecurityCustomizer {
        return WebSecurityCustomizer {
            it.ignoring().requestMatchers(PathRequest.toStaticResources().atCommonLocations())
                .requestMatchers("/error")
                .requestMatchers("/h2-console/**")
        }
    }

    @Bean
    fun securityFilterChain(
        http: HttpSecurity
    ): SecurityFilterChain {
        return http.authorizeHttpRequests {
            it.requestMatchers("/", "/users", "user/login/**", "/login*").permitAll()
                .requestMatchers("/mypage").hasRole("USER")
                .requestMatchers("/messages").hasRole("MANAGER")
                .requestMatchers("/config").hasRole("ADMIN")
                .anyRequest().authenticated()
        }.formLogin {
            it.loginPage("/login")
                .loginProcessingUrl("/login_proc")
                .defaultSuccessUrl("/")
                .successHandler(customFormAuthenticationSuccessHandler)
                .failureHandler(customFormAuthenticationFailureHandler)
                .authenticationDetailsSource(customFormWebAuthenticationDetailsSource)
                .permitAll()
        }.exceptionHandling {
            it.accessDeniedHandler(customFormAccessDeniedHandler)
        }.csrf {
            it.disable()
        }.headers {
            it.frameOptions { frameOption -> frameOption.disable() }
        }.build()
    }
}
