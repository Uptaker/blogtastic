package com.tammeoja.blog.images

import jakarta.persistence.Entity
import jakarta.persistence.Id
import jakarta.persistence.Table
import java.time.Instant
import java.util.*
import java.util.UUID.randomUUID

@Entity
@Table(name ="images")
data class Image (
  @Id val id: UUID = randomUUID(),
  val file: ByteArray,
  val mimeType: String,
  val createdAt: Instant = Instant.now()
) {

  override fun equals(other: Any?): Boolean {
    if (this === other) return true
    if (javaClass != other?.javaClass) return false

    other as Image

    return file.contentEquals(other.file)
  }

  override fun hashCode(): Int {
    return file.contentHashCode()
  }
}