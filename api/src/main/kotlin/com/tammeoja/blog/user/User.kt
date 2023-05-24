package com.tammeoja.blog.user

import com.fasterxml.jackson.annotation.JsonIgnore
import com.tammeoja.blog.user.User.Role.GUEST
import jakarta.persistence.*
import java.net.URI
import java.time.Instant
import java.util.*
import java.util.UUID.randomUUID

@Entity
@Table(name = "users")
data class User(
  val firstName: String,
  val lastName: String,
  val email: String,
  @JsonIgnore val passwordHash: String,
  val pictureUrl: URI? = null,
  @Enumerated(EnumType.STRING) val role: Role = GUEST,
  val createdAt: Instant = Instant.now(),
  @Id val id: UUID = randomUUID()
) {
  val name get() = "$firstName $lastName"

  init { require(email.contains('@')) { "Invalid email" } }

  enum class Role {
    GUEST, WRITER, ADMIN
  }
}