package com.tammeoja.blog.posts


import com.tammeoja.blog.user.User
import com.tammeoja.blog.user.User.Role.ADMIN
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.security.core.Authentication
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/posts")
class PostRoute(private val postService: PostService) {

  @GetMapping
  fun list(@RequestParam p: Int?) = postService.list(p ?: 1)

  @PreAuthorize("hasAnyAuthority('ADMIN', 'WRITER')")
  @PostMapping
  fun save(@RequestBody post: Post, auth: Authentication): Post {
    val user = auth.principal as User
    require(user.role == ADMIN || user.id == post.id) { "Forbidden" }
    return postService.save(post)
  }

  @GetMapping("/recent")
  fun recent() = postService.recent()

  @GetMapping("/random")
  fun random() = postService.random()

  @GetMapping("/{slug}")
  fun slug(@PathVariable slug: String) = postService.bySlug(slug)
}