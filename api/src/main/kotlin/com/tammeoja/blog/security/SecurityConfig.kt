package com.tammeoja.blog.security

import com.tammeoja.blog.auth.AuthService
import com.tammeoja.blog.user.UserRepository
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.http.SessionCreationPolicy.STATELESS
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.web.SecurityFilterChain
import org.springframework.security.web.context.SecurityContextHolderFilter


@Configuration
@EnableWebSecurity
@EnableMethodSecurity
class SecurityConfig(private val authManager: AuthManager, private val userRepository: UserRepository,
                     private val authService: AuthService) {

  @Bean fun filterChain(http: HttpSecurity): SecurityFilterChain = http
    .csrf().disable()
    .sessionManagement().sessionCreationPolicy(STATELESS)
    .and().addFilterBefore(AuthFilter(authService), SecurityContextHolderFilter::class.java)
    .authorizeHttpRequests {
      it.requestMatchers("/**").permitAll().anyRequest().authenticated()
    }
    .authenticationManager(authManager)
    .build()


  @Bean fun userDetailsService(): UserDetailsService = AuthDetailsService(userRepository)
}