import com.tammeoja.blog.posts.Post
import java.time.LocalDate

val today = LocalDate.now()
val tomorrow = today.plusDays(1)
val yesterday = today.minusDays(1)

val post = Post("Awesome Post!", "Hello! This is my post!\n\nFeel free to leave a comment!")
