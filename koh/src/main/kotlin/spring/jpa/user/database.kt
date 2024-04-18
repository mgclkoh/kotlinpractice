package spring.jpa.user

import java.sql.Connection
import java.sql.DriverManager
import java.sql.SQLException

fun main() {
    // MariaDB JDBC 드라이버 로드
    try {
        Class.forName("org.mariadb.jdbc.Driver")
    } catch (e: ClassNotFoundException) {
        println("MariaDB JDBC 드라이버를 찾을 수 없습니다.")
        e.printStackTrace()
        return
    }

    // 데이터베이스 연결
    val url = "jdbc:mariadb://irafe.com:3306/web2024?user=web2024&password=qwerasdf"
    var connection: Connection? = null
    try {
        connection = DriverManager.getConnection(url)
        println("데이터베이스에 성공적으로 연결되었습니다.")

        // 여기에서 데이터베이스 작업을 수행할 수 있습니다.

    } catch (e: SQLException) {
        println("데이터베이스에 연결하는 중 오류가 발생했습니다.")
        e.printStackTrace()
    } finally {
        connection?.close() // 연결을 닫습니다.
    }
}