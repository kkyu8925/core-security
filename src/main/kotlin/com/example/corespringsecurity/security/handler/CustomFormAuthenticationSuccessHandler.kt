package com.example.corespringsecurity.security.handler

import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.security.core.Authentication
import org.springframework.security.web.DefaultRedirectStrategy
import org.springframework.security.web.RedirectStrategy
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler
import org.springframework.security.web.savedrequest.HttpSessionRequestCache
import org.springframework.security.web.savedrequest.RequestCache
import org.springframework.stereotype.Component

@Component
class CustomFormAuthenticationSuccessHandler : SimpleUrlAuthenticationSuccessHandler() {

    private val requestCache: RequestCache = HttpSessionRequestCache()
    private val redirectStrategy: RedirectStrategy = DefaultRedirectStrategy()

    override fun onAuthenticationSuccess(
        request: HttpServletRequest, response: HttpServletResponse, authentication: Authentication
    ) {
        val savedRequest = requestCache.getRequest(request, response)

        if (savedRequest == null) {
            redirectStrategy.sendRedirect(request, response, "/")
        } else {
            redirectStrategy.sendRedirect(request, response, savedRequest.redirectUrl)
        }
    }
}
