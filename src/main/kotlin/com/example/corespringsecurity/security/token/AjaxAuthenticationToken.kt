package com.example.corespringsecurity.security.token

import org.springframework.security.authentication.AbstractAuthenticationToken
import org.springframework.security.core.GrantedAuthority

class AjaxAuthenticationToken : AbstractAuthenticationToken {
    private val principal: Any
    private var credentials: Any?

    constructor(principal: Any, credentials: Any) : super(null) {
        this.principal = principal
        this.credentials = credentials
    }

    constructor(principal: Any, credentials: Any?, authorities: Collection<GrantedAuthority>) : super(authorities) {
        this.principal = principal
        this.credentials = credentials
    }

    override fun getCredentials(): Any? {
        return credentials
    }

    override fun getPrincipal(): Any {
        return principal
    }
}
