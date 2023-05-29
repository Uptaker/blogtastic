package com.tammeoja.blog.images

import com.tammeoja.blog.posts.PostRepository
import com.tammeoja.blog.user.User
import com.tammeoja.blog.utils.Response
import org.springframework.data.repository.findByIdOrNull
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.security.core.Authentication
import org.springframework.web.bind.annotation.*
import org.springframework.web.multipart.MultipartFile
import java.util.*

@RestController
@RequestMapping("/api/images")
class ImageRoute(
  private val postRepository: PostRepository,
  private val imageRepository: ImageRepository
) {

  @PreAuthorize("hasAnyAuthority('ADMIN', 'WRITER')")
  @PostMapping("/post/{postId}")
  fun saveForPost(@RequestParam("image") file: MultipartFile, @PathVariable postId: UUID, auth: Authentication) {
    val post = postRepository.findByIdOrNull(postId) ?: error("No such post")
    val user = auth.principal as User
    require(post.userId == user.id) { "Forbidden" }
    imageRepository.save(Image(postId, file.bytes, file.contentType!!))
  }

  @GetMapping("/{id}")
  fun image(@PathVariable id: UUID, res: Response): ResponseEntity<ByteArray> {
    return imageRepository.findByIdOrNull(id)?.let {
      ResponseEntity.ok().contentType(MediaType.parseMediaType(it.mimeType)).body(it.file)
    } ?: ResponseEntity.notFound().build()
  }
}