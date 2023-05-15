package com.tammeoja.blog.posts

import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Sort
import post
import java.util.*
import java.util.UUID.randomUUID

class PostServiceTest {

  private val repository = mockk<PostRepository>()
  private val service = PostService(repository)

  @Test
  fun list() {
    every { repository.findAll(PageRequest.of(0, 10)).toList() } returns listOf(post)
    every { repository.findAll(PageRequest.of(1, 10)).toList() } returns emptyList()

    assertEquals(service.list(1), listOf(post))
    assertEquals(service.list(2), emptyList<Post>())
  }

  @Test
  fun `save and update`() {
    every { repository.save(any()) } returns post

    val updatedPost = post.copy(title = "UPDATED! Awesome Post!")
    val post2 = post.copy(id = randomUUID())

    service.save(post)
    service.save(updatedPost)
    service.save(post2)
    verify(exactly = 3) { repository.save(any()) }
  }
}