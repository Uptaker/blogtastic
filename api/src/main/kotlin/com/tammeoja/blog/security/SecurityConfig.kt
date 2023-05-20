package com.tammeoja.blog.security

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.web.SecurityFilterChain

@Configuration
@EnableWebSecurity
class SecurityConfig {
  val OPEN_URLS = arrayOf("/**")

  @Bean fun filterChain(http: HttpSecurity): SecurityFilterChain = http
    .csrf().disable()
    .authorizeHttpRequests {
      it.requestMatchers(*OPEN_URLS).permitAll().anyRequest().authenticated()
    }
    .httpBasic()
    .and().build()
}