package com.example.corespringsecurity.security.service

import com.example.corespringsecurity.repository.UserRepository
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.stereotype.Service
import java.util.stream.Collectors
import kotlin.jvm.optionals.getOrElse

@Service
class CustomUserDetailsService(
    private val userRepository: UserRepository
) : UserDetailsService {

    override fun loadUserByUsername(username: String): UserDetails {
        val account = userRepository.findByUsername(username).getOrElse {
            throw UsernameNotFoundException("UsernameNotFoundException")
        }

        val collect = account.userRoles
            .stream()
            .map { it.roleName }
            .collect(Collectors.toSet())
            .stream().map { SimpleGrantedAuthority(it) }
            .collect(Collectors.toList())

        return AccountContext(account, collect)
    }
}
