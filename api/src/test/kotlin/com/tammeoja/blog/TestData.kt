import com.tammeoja.blog.posts.Post
import com.tammeoja.blog.user.User
import java.time.LocalDate

val today = LocalDate.now()
val tomorrow = today.plusDays(1)
val yesterday = today.minusDays(1)

val user = User("Markus", "Tammeoja", "m@t.com", "123123123")
val post = Post("Awesome Post!", "Hello! This is my post!\n\nFeel free to leave a comment!", "/awesome", user.id)
