package com.tammeoja.blog.user

import com.fasterxml.jackson.annotation.JsonIgnore
import com.tammeoja.blog.user.User.Role.WRITER
import jakarta.persistence.*
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.UserDetails
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
  @Enumerated(EnumType.STRING) val role: Role = WRITER,
  val createdAt: Instant = Instant.now(),
  @Id val id: UUID = randomUUID()
): UserDetails {
  val name get() = "$firstName $lastName"

  init {
    require(email.contains('@')) { "Invalid email" }
  }

  @JsonIgnore
  override fun getPassword() = passwordHash

  @JsonIgnore override fun getUsername() = id.toString()
  @JsonIgnore override fun getAuthorities() = listOf(SimpleGrantedAuthority(role.name))
  @JsonIgnore override fun isAccountNonExpired() = true
  @JsonIgnore override fun isAccountNonLocked() = true
  @JsonIgnore override fun isCredentialsNonExpired() = true
  @JsonIgnore override fun isEnabled() = true

  enum class Role {
    ADMIN, WRITER
  }
}