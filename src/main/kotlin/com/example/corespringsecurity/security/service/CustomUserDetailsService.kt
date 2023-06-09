package com.example.corespringsecurity.security.service

import com.example.corespringsecurity.repository.UserRepository
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.stereotype.Service

@Service
class CustomUserDetailsService(
    private val userRepository: UserRepository
) : UserDetailsService {

    override fun loadUserByUsername(username: String): UserDetails {
        val account =
            userRepository.findByUsername(username) ?: throw UsernameNotFoundException("UsernameNotFoundException")

        val roles: MutableList<GrantedAuthority> = ArrayList()
        roles.add(SimpleGrantedAuthority(account.role))

        return AccountContext(account, roles)
    }
}
