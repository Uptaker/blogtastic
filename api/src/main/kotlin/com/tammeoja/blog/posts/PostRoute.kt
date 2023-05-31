package com.tammeoja.blog.posts


import com.tammeoja.blog.tags.PostTagRepository
import com.tammeoja.blog.user.User
import com.tammeoja.blog.user.User.Role.ADMIN
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.security.core.Authentication
import org.springframework.web.bind.annotation.*
import java.util.*

@RestController
@RequestMapping("/api/posts")
class PostRoute(
  private val postService: PostService,
  private val postTagRepository: PostTagRepository
) {

  @GetMapping
  fun list(@RequestParam p: Int?) = postService.list(p ?: 1)

  @PreAuthorize("hasAnyAuthority('ADMIN', 'WRITER')")
  @PostMapping
  fun save(@RequestBody postSave: PostSaveRequest, auth: Authentication): Post {
    val user = auth.principal as User
    require(user.role == ADMIN || user.id == postSave.post.id) { "Forbidden" }
    return postService.save(postSave)
  }

  @GetMapping("/recent")
  fun recent() = postService.recent()

  @GetMapping("/random")
  fun random() = postService.random()

  @GetMapping("/{slug}")
  fun slug(@PathVariable slug: String) = postService.bySlug(slug)

  @GetMapping("/{postId}/tags")
  fun tags(@PathVariable postId: UUID) = postTagRepository.findAllByPostId(postId)
}

data class PostSaveRequest(val post: Post, val tags: List<String>)