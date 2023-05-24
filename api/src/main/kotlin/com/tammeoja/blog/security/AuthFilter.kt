package com.tammeoja.blog.security

import com.tammeoja.blog.auth.AuthService
import com.tammeoja.blog.user.User
import com.tammeoja.blog.utils.Request
import com.tammeoja.blog.utils.Response
import jakarta.servlet.FilterChain
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.stereotype.Component
import org.springframework.web.filter.OncePerRequestFilter

@Component
class AuthFilter(
  private val authService: AuthService
): OncePerRequestFilter() {
  override fun doFilterInternal(request: Request, response: Response, filterChain: FilterChain) {
    authService.cookie(request)?.let {
      val user = authService.validateToken(it.value)
      user?.let { u -> setPrincipal(u) } ?: authService.clearAuthCookie(request, response, it)
    }
    filterChain.doFilter(request, response)
  }

  private fun setPrincipal(user: User) {
    SecurityContextHolder.getContext().authentication =
      UsernamePasswordAuthenticationToken(user, user.passwordHash, user.authorities)
  }
}
