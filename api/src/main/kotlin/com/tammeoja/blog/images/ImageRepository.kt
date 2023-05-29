package com.tammeoja.blog.images

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface ImageRepository: JpaRepository<Image, UUID> {}