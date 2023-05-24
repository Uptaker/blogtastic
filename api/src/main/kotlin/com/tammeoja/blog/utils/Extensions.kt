package com.tammeoja.blog.utils

import com.tammeoja.blog.auth.AuthToken
import jakarta.servlet.http.Cookie

fun Cookie.secure(token: AuthToken, path: String = "/api"): Cookie {
  secure = true
  isHttpOnly = true
  this.path = path
  maxAge = (token.expiresAt.epochSecond - token.createdAt.epochSecond).toInt()
  return this
}