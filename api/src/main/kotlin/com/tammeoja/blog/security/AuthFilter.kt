package com.tammeoja.blog.security

import com.tammeoja.blog.auth.AuthService
import jakarta.servlet.FilterChain
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.stereotype.Component
import org.springframework.web.filter.OncePerRequestFilter

@Component
class AuthFilter(
  private val authService: AuthService
): OncePerRequestFilter() {
  override fun doFilterInternal(request: HttpServletRequest, response: HttpServletResponse, filterChain: FilterChain) {
    authService.cookie(request)?.let {
      val user = authService.validateToken(it.value)
      if (user != null) {
        SecurityContextHolder.getContext().authentication =
          UsernamePasswordAuthenticationToken(user, user.passwordHash, user.authorities)
      } else {
        authService.clearAuthCookie(request, response, it)
      }
    }
    filterChain.doFilter(request, response)
  }
}