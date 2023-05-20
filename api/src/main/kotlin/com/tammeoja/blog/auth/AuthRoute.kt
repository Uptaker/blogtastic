package com.tammeoja.blog.auth

import jakarta.persistence.Entity
import jakarta.persistence.Id
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import java.net.URI
import java.util.*
import java.util.UUID.randomUUID

@Controller
class AuthRoute {

  @GetMapping("/me")
  fun me(): AuthUser {
    return AuthUser("Some", "User", "some@email.com")
  }
}

@Entity
data class AuthUser(
  val firstName: String,
  val lastName: String,
  val email: String,
  val pictureUrl: URI? = null,
  @Id val id: UUID = randomUUID()
) {
  val name get() = "$firstName $lastName"

  init {
    require(email.contains('@')) { "Invalid email" }
  }
}