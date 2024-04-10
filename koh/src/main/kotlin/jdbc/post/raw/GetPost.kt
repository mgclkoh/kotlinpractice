package jdbc.post.raw

import jdbc.DataSourceFactory
import jdbc.post.mapPost
import java.util.*

fun main() {
    print("Get - id ? ")
    val id = Scanner(System.`in`).use { it.nextInt() }
    val sql = """
    select id, title, content, user_id, first_name, pub_date, last_modified
    from post where id=?""".trimIndent()

    DataSourceFactory.dataSource.connection.use { conn ->
        conn.prepareStatement(sql).use { ps ->
            ps.setInt(1, id)
            ps.executeQuery().use { rs ->
                if (rs.next()) {
                    val post = mapPost(rs)
                    println(post)
                } else {
                    println("포스트 없음")
                }
            }
        }
    }
}