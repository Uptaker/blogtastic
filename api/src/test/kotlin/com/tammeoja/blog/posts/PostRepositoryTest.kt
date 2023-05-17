package com.tammeoja.blog.posts

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Disabled
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager
import post
import java.util.UUID.randomUUID
import kotlin.jvm.optionals.getOrNull

@DataJpaTest //(properties = ["spring.datasource.url=jdbc:tc:postgresql:15.3:///testdb"])
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class PostRepositoryTest @Autowired constructor(
  val manager: TestEntityManager,
  val postRepository: PostRepository

) {

  val post2 = post.copy(content = "[REDACTED]", id = randomUUID())

  @BeforeEach
  fun setUp() {
    manager.persist(post)
    manager.persist(post2)
    manager.flush()
  }

  @Test
  fun `save and load`() {
    var found = postRepository.findById(post.id).getOrNull()
    assertThat(found).isEqualTo(post)

    val updatedPost = post.copy(content = "[REDACTED]")
    postRepository.save(updatedPost)
    found = postRepository.findById(post.id).getOrNull()
    assertThat(found).isEqualTo(updatedPost)
  }

  @Test @Disabled
  fun `list and delete`()  {
    postRepository.save(post)
    postRepository.save(post2)

    assertThat(postRepository.findAll().size).isEqualTo(2)
    postRepository.delete(post)
    assertThat(postRepository.findAll()).isEqualTo(listOf(post2))
  }
}