package com.tammeoja.blog.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.security.web.context.HttpSessionSecurityContextRepository
import org.springframework.security.web.context.SecurityContextRepository

@Configuration
class BeansConfig {
  @Bean fun passwordEncoder(): PasswordEncoder = BCryptPasswordEncoder()
  @Bean fun securityContextRepository(): SecurityContextRepository = HttpSessionSecurityContextRepository()
}