package com.tammeoja.blog.auth

import com.tammeoja.blog.user.User
import com.tammeoja.blog.utils.Request
import com.tammeoja.blog.utils.Response
import jakarta.persistence.*
import org.springframework.security.core.Authentication
import org.springframework.web.bind.annotation.*
import java.util.*

@RestController
@RequestMapping("/api/user")
class AuthRoute(private val authService: AuthService) {

  @GetMapping
  fun me(res: Response, auth: Authentication?): User? {
    val user = auth?.principal
    return if (user is User) user else null
  }

  @PostMapping("/register")
  fun register(@RequestBody form: UserRegistrationRequest, res: Response): User {
    return authService.register(form).also { authService.initSession(it, res) }
  }

  @PostMapping("/login")
  fun login(@RequestBody req: UserLoginRequest, res: Response): User {
    return authService.login(req).also { authService.initSession(it, res) }
  }

  @GetMapping("/logout")
  fun logout(req: Request, res: Response) = authService.clearAuthCookie(req, res)
}

data class UserRegistrationRequest(val firstName: String, val lastName: String, val email: String, val password: String)
data class UserLoginRequest(val email: String, val password: String)