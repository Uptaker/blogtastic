package com.tammeoja.blog.tags

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface PostTagRepository: JpaRepository<PostTag, String> {
  fun deleteAllByTagId(id: String)
}