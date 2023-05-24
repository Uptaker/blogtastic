package com.tammeoja.blog.auth

import com.tammeoja.blog.user.User
import com.tammeoja.blog.user.UserRepository
import com.tammeoja.blog.utils.Request
import com.tammeoja.blog.utils.Response
import com.tammeoja.blog.utils.secure
import jakarta.servlet.http.Cookie
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service
import java.time.Instant
import kotlin.jvm.optionals.getOrNull

@Service
class AuthService(
  private val tokenRepository: AuthTokenRepository,
  private val userRepository: UserRepository,
  private val passwordEncoder: PasswordEncoder
) {

  private val cookieName = "AUTH"

  fun cookie(req: Request) = req.cookies?.find { it.name == cookieName }

  fun login(req: UserLoginRequest): User {
    val errorMessage = "Invalid email or password"
    val user = userRepository.findByEmail(req.email) ?: error(errorMessage)
    require(passwordEncoder.matches(req.password, user.passwordHash)) { errorMessage }
    return user
  }

  fun register(form: UserRegistrationRequest): User {
    require(userRepository.findByEmail(form.email) == null) { "Email already exists" }
    val passwordHash = passwordEncoder.encode(form.password)
    return userRepository.save(User(form.firstName, form.lastName, form.email, passwordHash))
  }

  fun initSession(user: User, res: Response) {
    val token = tokenRepository.save(AuthToken(user.id))
    val cookie = Cookie(cookieName, token.token).secure(token)
    res.addCookie(cookie)
  }

  fun validateToken(token: String): User? {
    val authToken = tokenRepository.findById(token).getOrNull() ?: return null
    if (authToken.expiresAt < Instant.now()) return null
    return userRepository.findById(authToken.userId).getOrNull()
  }

  fun clearAuthCookie(req: Request, res: Response, cookie: Cookie? = null) {
    val cookieAuth = cookie ?: req.cookies?.find { it.name == cookieName }
    cookieAuth?.let {
      it.maxAge = 0
      res.addCookie(cookieAuth)
      tokenRepository.findById(it.value).getOrNull()?.let { token ->
        tokenRepository.delete(token)
      }
    }
  }
}