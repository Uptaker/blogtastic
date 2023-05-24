package com.tammeoja.blog.posts


import com.tammeoja.blog.auth.AuthService
import jakarta.servlet.http.HttpServletRequest
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.security.core.Authentication
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/posts")
class PostRoute(
  private val postService: PostService,
  private val authService: AuthService
) {

  @GetMapping
  fun list(@RequestParam p: Int?) = postService.list(p ?: 1)

  @PreAuthorize("hasAnyAuthority('ADMIN', 'WRITER')")
  @PostMapping
  fun save(@RequestBody post: Post, auth: Authentication?, req: HttpServletRequest): Post {
    return postService.save(post)
  }

  @GetMapping("/recent")
  fun recent() = postService.recent()
}