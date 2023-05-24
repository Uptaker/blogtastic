package com.tammeoja.blog.auth

import com.tammeoja.blog.utils.TextGenerator
import jakarta.persistence.Entity
import jakarta.persistence.Id
import jakarta.persistence.Table
import java.time.Instant
import java.time.LocalDate
import java.time.ZoneOffset.systemDefault
import java.util.*

@Entity
@Table(name = "auth_tokens")
data class AuthToken(
  val userId: UUID,
  @Id val token: String =  TextGenerator.random(),
  val expiresAt: Instant = LocalDate.now().plusMonths(2).atStartOfDay().atZone(systemDefault()).toInstant(),
  val createdAt: Instant = Instant.now()
)
