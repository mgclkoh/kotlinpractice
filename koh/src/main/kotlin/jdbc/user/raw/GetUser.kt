package jdbc.user.raw

import jdbc.DataSourceFactory
import jdbc.user.User2
import java.util.*

fun main() {
    print("Get - id ? ")
    val id = Scanner(System.`in`).use { it.nextInt() }
    val sql =
        "select id, username, password, first_name, date_joined from user where id=?"
    
    DataSourceFactory.dataSource.connection.use { conn ->
        conn.prepareStatement(sql).use { ps ->
            ps.setInt(1, id)
            ps.executeQuery().use { rs ->
                if (rs.next()) {
                    val user = 
                        User2( id = rs.getInt("id"), username = rs.getString("username"),
                            password = rs.getString("password"),
                            firstName = rs.getString("first_name"),
                            dateJoined = rs.getTimestamp("date_joined").toLocalDateTime())
                    println(user)
                } else {
                    println("사용자 없음")
                }
            }
        }
    }
}
