package com.tammeoja.blog.posts


import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/posts")
class PostRoute(private val postService: PostService) {

  @GetMapping
  fun list(@RequestParam p: Int?) = postService.list(p ?: 1)

  @PreAuthorize("hasAnyAuthority('ADMIN', 'WRITER')")
  @PostMapping
  fun save(@RequestBody post: Post) = postService.save(post)

  @GetMapping("/recent")
  fun recent() = postService.recent()
}