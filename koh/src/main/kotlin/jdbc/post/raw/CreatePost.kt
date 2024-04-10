package jdbc.post.raw

import jdbc.DataSourceFactory
import jdbc.bcryptHashed
import jdbc.user.User2
import java.util.*

fun main() {
    print("Create - username(username) password first_name ? ")
    val scanner = Scanner(System.`in`)
    val user =
        User2(username = scanner.next(), password = scanner.next().bcryptHashed,
            firstName = scanner.next())
    scanner.close()
    val sql = "insert user(username, password, first_name) values(?, ?, ?)"
    try {
        DataSourceFactory.dataSource.connection.use { conn ->
            conn.prepareStatement(sql).use { ps ->
                ps.setString(1, user.username)
                ps.setString(2, user.password)
                ps.setString(3, user.firstName)
                ps.executeUpdate()
                println("저장 완료")
            }
        }
    } catch (e: Exception) {
        println(e.message)
    }
}