package com.tammeoja.blog.auth

import com.tammeoja.blog.user.User
import com.tammeoja.blog.user.UserRepository
import com.tammeoja.blog.utils.Request
import com.tammeoja.blog.utils.Response
import jakarta.persistence.*
import org.springframework.security.core.Authentication
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.web.bind.annotation.*
import java.util.*

@RestController
@RequestMapping("/api/user")
class AuthRoute(
  private val passwordEncoder: PasswordEncoder,
  private val userRepository: UserRepository,
  private val authService: AuthService,
) {

  @GetMapping
  fun me(res: Response, auth: Authentication?): User? {
    val user = auth?.principal
    return if (user is User) user else null
  }

  @PostMapping("/register")
  fun register(@RequestBody req: UserRegistrationRequest, res: Response): User {
    require(userRepository.findByEmail(req.email) == null) { "Email already exists" }
    val passwordHash = passwordEncoder.encode(req.password)
    return userRepository.save(User(req.firstName, req.lastName, req.email, passwordHash)).also {
      authService.initSession(it, res)
    }
  }

  @PostMapping("/login")
  fun login(@RequestBody req: UserLoginRequest, res: Response): User {
    return authService.auth(req).also { authService.initSession(it, res) }
  }

  @GetMapping("/logout")
  fun logout(req: Request, res: Response) {
    authService.clearAuthCookie(req, res)
  }

}

data class UserRegistrationRequest(val firstName: String, val lastName: String, val email: String, val password: String)
data class UserLoginRequest(val email: String, val password: String)