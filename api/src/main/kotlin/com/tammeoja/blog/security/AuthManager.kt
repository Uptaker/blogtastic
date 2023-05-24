package com.tammeoja.blog.security

import com.tammeoja.blog.user.UserRepository
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.Authentication
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Component
import org.springframework.stereotype.Service
import java.util.*

@Component
class AuthManager(
  private val passwordEncoder: PasswordEncoder,
  private val userRepository: UserRepository
): AuthenticationManager {

  override fun authenticate(authentication: Authentication?): Authentication? {
    val email = authentication?.name ?: return null
    val user = userRepository.findByEmail(email) ?: return null
    val password = authentication.credentials.toString()
    return if (!passwordEncoder.matches(password, user.password)) null
    else UsernamePasswordAuthenticationToken(user, user.passwordHash, user.authorities)
  }
}

@Service
class AuthDetailsService(private val userRepository: UserRepository): UserDetailsService {
  override fun loadUserByUsername(username: String?): UserDetails {
    return userRepository.findById(UUID.fromString(username)).get()
  }
}