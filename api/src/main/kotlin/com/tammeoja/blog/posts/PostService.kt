package com.tammeoja.blog.posts

import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Sort
import org.springframework.data.domain.Sort.Direction.DESC
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import java.util.*

@Service
class PostService(private val postRepository: PostRepository) {
  fun defaultSort(vararg tables: String) = Sort.by(DESC, "createdAt", *tables)

  fun list(page: Int): List<Post> = postRepository.findAll(PageRequest.of(maxOf(page - 1, 0), 10).withSort(defaultSort())).toList()

  fun save(post: Post): Post = postRepository.save(post)

  fun get(id: UUID): Post? = postRepository.findByIdOrNull(id)

  fun recent(limit: Int = 4): List<Post> = postRepository.findAll(defaultSort("updatedAt")).take(limit)
}
