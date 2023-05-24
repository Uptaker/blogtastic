package com.tammeoja.blog.security

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.http.SessionCreationPolicy.STATELESS
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.security.provisioning.InMemoryUserDetailsManager
import org.springframework.security.web.SecurityFilterChain


@Configuration
@EnableWebSecurity
@EnableMethodSecurity
class SecurityConfig {

  @Bean fun filterChain(http: HttpSecurity): SecurityFilterChain = http
    .csrf().disable()
    .sessionManagement().sessionCreationPolicy(STATELESS)
    .and().authorizeHttpRequests {
      it.requestMatchers("/**").permitAll().anyRequest().authenticated()
    }
    .build()

  @Bean fun passwordEncoder(): PasswordEncoder = BCryptPasswordEncoder()

  // TODO - this does nothing and its only purpose is to remove default log. find a better way
  @Bean fun userDetailsService(): UserDetailsService? = InMemoryUserDetailsManager()


}