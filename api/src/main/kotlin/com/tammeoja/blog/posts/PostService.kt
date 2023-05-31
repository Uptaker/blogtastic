package com.tammeoja.blog.posts

import com.tammeoja.blog.tags.PostTag
import com.tammeoja.blog.tags.PostTagRepository
import com.tammeoja.blog.user.UserRepository
import jakarta.transaction.Transactional
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
class PostService(
  private val postRepository: PostRepository,
  private val userRepository: UserRepository,
  private val postTagRepository: PostTagRepository
) {

  fun defaultSort(vararg tables: String) = Sort.by(DESC, "createdAt", *tables)

  fun list(page: Int): List<PostInList> {
    val posts = postRepository.findAll(PageRequest.of(maxOf(page - 1, 0), 10).withSort(defaultSort())).toList()
    return posts.groupBy { it.userId }.flatMap {(userId, posts) ->
      val user = userRepository.findById(userId).get()
      posts.map { PostInList(it, PostAuthor(user.id, user.name)) }
    }
  }

  @Transactional
  fun save(req: PostSaveRequest): Post {
    postRepository.apply {
      val saved = if (existsById(req.post.id)) save(req.post.copy(updatedAt = Instant.now())) else save(req.post)
      postTagRepository.deleteAllByPostId(saved.id)
      req.tags.forEach { postTagRepository.save(PostTag(saved.id, it)) }
      return saved
    }
  }

  fun get(id: UUID): Post? = postRepository.findByIdOrNull(id)

  fun bySlug(slug: String): PostInList? = postRepository.findBySlug(slug)?.let {
    PostInList(it, PostAuthor(it.userId, userRepository.findById(it.userId).get().name) )
  }

  fun recent(limit: Int = 4): List<Post> = postRepository.findAll(defaultSort("updatedAt")).take(limit)

  fun random(max: Int = 5): List<Post> = postRepository.count().let {
    val qty = it.toInt()
    val randomInts = generateSequence { Random.nextInt(0..qty) }
      .distinct()
      .take(if (qty >= max) max else qty)
      .toSet()
    return randomInts.mapNotNull {i ->
      val postPage = postRepository.findAll(PageRequest.of(i, 1))
      postPage.content.firstOrNull()
    }
  }
}
