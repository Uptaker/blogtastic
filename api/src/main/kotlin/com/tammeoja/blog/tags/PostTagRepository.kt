package com.tammeoja.blog.tags

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface PostTagRepository: JpaRepository<PostTag, String> {
  fun deleteAllByTagId(id: String)
  fun deleteAllByPostId(id: UUID)
  fun findAllByPostId(id: UUID): List<PostTag>
  fun countAllByTagId(id: String): Int
}