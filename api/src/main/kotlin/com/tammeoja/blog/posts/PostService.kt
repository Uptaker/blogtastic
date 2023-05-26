package com.tammeoja.blog.posts

import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Sort
import org.springframework.data.domain.Sort.Direction.DESC
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import java.time.Instant
import java.util.*
import kotlin.random.Random
import kotlin.random.nextInt


@Service
class PostService(private val postRepository: PostRepository) {

  fun defaultSort(vararg tables: String) = Sort.by(DESC, "createdAt", *tables)

  fun list(page: Int): List<Post> = postRepository.findAll(PageRequest.of(maxOf(page - 1, 0), 10).withSort(defaultSort())).toList()

  fun save(post: Post): Post {
    postRepository.apply {
      return if (existsById(post.id)) save(post.copy(updatedAt = Instant.now())) else save(post)
    }
  }

  fun get(id: UUID): Post? = postRepository.findByIdOrNull(id)

  fun bySlug(slug: String): Post? = postRepository.findBySlug(slug)

  fun recent(limit: Int = 4): List<Post> = postRepository.findAll(defaultSort("updatedAt")).take(limit)

  fun random(): List<Post> = postRepository.count().let {
    val qty = it.toInt()
    val randomInts = generateSequence { Random.nextInt(0..qty) }
      .distinct()
      .take(if (qty >= 5) 5 else qty)
      .toSet()
    return randomInts.mapNotNull {i ->
      val postPage = postRepository.findAll(PageRequest.of(i, 1))
      postPage.content.firstOrNull()
    }
  }
}
