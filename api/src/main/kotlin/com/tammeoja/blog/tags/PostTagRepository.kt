package com.tammeoja.blog.tags

import org.springframework.data.jpa.repository.JpaRepository

interface PostTagRepository: JpaRepository<PostTag, String> {}