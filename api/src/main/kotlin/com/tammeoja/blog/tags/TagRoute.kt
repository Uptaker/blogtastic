package com.tammeoja.blog.tags

import org.springframework.data.repository.findByIdOrNull
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/tags")
class TagRoute(
  private val tagRepository: TagRepository,
  private val postTagRepository: PostTagRepository
) {

  @GetMapping
  fun list(): List<Tag> = tagRepository.findAll()

  @GetMapping("/count")
  fun tagPostCount(): List<PostTagCount> =
    tagRepository.findAll().map { PostTagCount(it, postTagRepository.countAllByTagId(it.id)) }

  @PostMapping
  fun save(@RequestBody tag: Tag) = tagRepository.save(tag)

  @DeleteMapping("/{id}")
  fun delete(@PathVariable id: String) = tagRepository.findByIdOrNull(id)?.let {
      postTagRepository.deleteAllByTagId(it.id)
      tagRepository.delete(it)
  }

}

data class PostTagCount(val tag: Tag, val count: Int)