package com.example.corespringsecurity.security.service

import com.example.corespringsecurity.domain.entity.Account
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.userdetails.User

class AccountContext(
    val account: Account, authorities: Collection<GrantedAuthority>
) : User(
    account.username, account.password, authorities
)
