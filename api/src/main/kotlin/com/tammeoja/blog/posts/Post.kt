package com.tammeoja.blog.posts

import jakarta.persistence.Entity
import jakarta.persistence.Id
import jakarta.persistence.Table
import java.time.Instant
import java.time.LocalDate
import java.time.ZoneId.systemDefault
import java.util.*
import java.util.UUID.randomUUID

@Entity
@Table(name = "posts")
data class Post(
  val title: String,
  val content: String,
  val slug: String,
  val userId: UUID,
  val subheadline: String? = null,
  val date: LocalDate = LocalDate.now(),
  val createdAt: Instant = Instant.now(),
  val updatedAt: Instant? = null,
  @Id val id: UUID = randomUUID()
) {
  val fullDate get() = LocalDate.ofInstant(createdAt, systemDefault()).toString().replace('-', '/')
}

data class PostInList(val details: Post, val author: PostAuthor)
data class PostAuthor (val userId: UUID, val name: String)

