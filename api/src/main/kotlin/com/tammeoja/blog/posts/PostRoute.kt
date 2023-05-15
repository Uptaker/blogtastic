package com.tammeoja.blog.posts

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/posts")
class PostRoute(
  private val postService: PostService
) {

  @GetMapping
  fun list(@RequestParam p: Int?) = postService.list(p ?: 1)

  @PostMapping
  fun save(@RequestBody post: Post) = postService.save(post)

  @GetMapping("/recent")
  fun recent() = postService.recent()
}