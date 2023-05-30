package com.tammeoja.blog.tags

import org.springframework.data.jpa.repository.JpaRepository

interface TagRepository: JpaRepository<Tag, String> {}