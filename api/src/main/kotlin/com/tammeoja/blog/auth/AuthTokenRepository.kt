package com.tammeoja.blog.auth

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface AuthTokenRepository: JpaRepository<AuthToken, String>