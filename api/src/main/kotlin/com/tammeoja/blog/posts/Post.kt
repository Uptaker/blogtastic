package com.tammeoja.blog.posts

import jakarta.persistence.Entity
import jakarta.persistence.Id
import java.time.Instant
import java.time.LocalDate
import java.util.*

@Entity
data class Post(
  val title: String,
  val content: String,
  val date: LocalDate = LocalDate.now(),
  val createdAt: Instant = Instant.now(),
  val updatedAt: Instant? = null,
  @Id val id: UUID? = UUID.randomUUID()
) {
  enum class Tags {
    TECH, FUN, TIL, PROGRAMMING, REVIEWS, GAMES, LIFE, RANDOM
  }
}

