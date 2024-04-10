package jdbc.user.raw

import jdbc.DataSourceFactory
import jdbc.Page
import jdbc.user.User2
import java.util.*

fun main() {
    print("List - pageNumber pageSize ? ")
    val scanner = Scanner(System.`in`)
    val page = Page(scanner.nextInt(), scanner.nextInt())
    scanner.close()

    val sql =
        "select id, username, first_name, date_joined from user order by id desc limit ?,?"
    val userList = mutableListOf<User2>()
    DataSourceFactory.dataSource.connection.use { conn ->
        conn.prepareStatement(sql).use { ps ->
            ps.setInt(1, page.offset)
            ps.setInt(2, page.size)

            ps.executeQuery().use { rs ->
                while (rs.next()) {
                    val user =
                        User2(id = rs.getInt("id"), username = rs.getString("username"),
                            firstName = rs.getString("first_name"),
                            dateJoined = rs.getTimestamp("date_joined").toLocalDateTime())
                    userList.add(user)
                }
            }
        }
    }
    println(userList)
}