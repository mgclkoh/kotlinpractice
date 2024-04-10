package jdbc.user.raw



import jdbc.DataSourceFactory
import jdbc.user.User2
import java.sql.Connection
import java.sql.PreparedStatement
import java.sql.ResultSet
import java.util.*

fun main() {
    print("Get - id ? ")
    val id = Scanner(System.`in`).use { it.nextInt() }
    val sql =
        "select id, username, password, first_name, date_joined from user where id=?"

    // 1. dataSource에서 connection 가져오기
    val conn: Connection = DataSourceFactory.dataSource.connection

    // 2. connection에서 preparedStatment 만들기
    val ps: PreparedStatement = conn.prepareStatement(sql)
    ps.setInt(1, id)

    // 3. preparedStatement를 실행해서 resultSet 리턴
    val rs: ResultSet = ps.executeQuery()
    if (rs.next()) {
        val user = User2(id = rs.getInt("id"), username = rs.getString("username"),
            password = rs.getString("password"),
            firstName = rs.getString("first_name"),
            dateJoined = rs.getTimestamp("date_joined").toLocalDateTime())
        println(user)
    } else {
        println("사용자 없음")
    }

    // close resultSet
    rs.close()

    // close preparedStatement
    ps.close()

    // close connection
    conn.close()
}