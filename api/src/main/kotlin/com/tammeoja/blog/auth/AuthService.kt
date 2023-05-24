package com.tammeoja.blog.auth

import com.tammeoja.blog.security.AuthManager
import com.tammeoja.blog.user.User
import com.tammeoja.blog.user.UserRepository
import com.tammeoja.blog.utils.Request
import com.tammeoja.blog.utils.Response
import jakarta.servlet.http.Cookie
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service
import java.time.Instant
import kotlin.jvm.optionals.getOrNull

@Service
class AuthService(
  private val tokenRepository: AuthTokenRepository,
  private val authManager: AuthManager,
) {

  private val cookieName = "AUTH"


  @Autowired
  private lateinit var userRepository: UserRepository

  @Autowired
  private lateinit var passwordEncoder: PasswordEncoder

  fun cookie(req: Request) = req.cookies?.find { it.name == cookieName }

  fun auth(req: UserLoginRequest): User {
    val errorMessage = "Invalid email or password"
    val user = userRepository.findByEmail(req.email) ?: error(errorMessage)
    require(passwordEncoder.matches(req.password, user.passwordHash)) { errorMessage }
    return user
  }

  fun initSession(user: User, res: Response) {
    val token = tokenRepository.save(AuthToken(user.id))
    val cookie = Cookie(cookieName, token.token)
    cookie.secure = true
    cookie.isHttpOnly = true
    cookie.path = "/api"
    cookie.maxAge = (token.expiresAt.epochSecond - token.createdAt.epochSecond).toInt()
    res.addCookie(cookie)
    authManager.authenticate(UsernamePasswordAuthenticationToken(user, user.passwordHash, user.authorities))
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