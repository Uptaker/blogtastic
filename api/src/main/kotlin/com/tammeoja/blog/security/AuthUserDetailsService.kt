package com.tammeoja.blog.security

import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService

class AuthUserDetailsService: UserDetailsService {
  override fun loadUserByUsername(username: String?): UserDetails {
    TODO("Not yet implemented")

  }
}