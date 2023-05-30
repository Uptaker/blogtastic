package com.tammeoja.blog.tags

import jakarta.persistence.Entity
import jakarta.persistence.Id
import jakarta.persistence.Table
import java.util.*

@Entity
@Table(name = "tags")
data class Tag(@Id var id: String, val color: String) {
  init {
    require(color.startsWith('#') && color.length == 7)
    id = id.uppercase()
  }
}

@Entity
@Table(name = "post_tags")
data class PostTag(val postId: UUID, val tagId: String, @Id val id: String = "$postId:$tagId")
