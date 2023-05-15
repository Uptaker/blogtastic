package com.tammeoja.blog.posts

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface PostRepository: JpaRepository<Post, UUID>
