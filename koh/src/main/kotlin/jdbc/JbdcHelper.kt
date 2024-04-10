package jdbc


import java.sql.PreparedStatement
import java.sql.ResultSet
import javax.sql.DataSource

/**
 * JDBC helper utilities
 * @author Jacob
 */
class JdbcHelper(val ds: DataSource) {

    /**
     * 목록을 가져오는 helper method
     * @return 결과 리스트. 결과가 없을 경우 빈 리스트
     */
    inline fun <T> list(sql: String, mapRow: (ResultSet) -> T,
                        vararg params: Any): List<T> {
        ds.connection.use { conn ->
            conn.prepareStatement(sql).use { ps ->
                setParameters(ps, params)
                val list = mutableListOf<T>()
                val rs = ps.executeQuery()
                while (rs.next()) list.add(mapRow(rs))
                return list
            }
        }
    }

    /**
     * 한 건을 가져오는 helper method
     * @return 한 건 오브젝트. 결과가 없을 경우 null
     */
    inline fun <T> get(sql: String, mapRow: (ResultSet) -> T,
                       vararg params: Any): T? {
        ds.connection.use { conn ->
            conn.prepareStatement(sql).use { ps ->
                setParameters(ps, params)
                val rs = ps.executeQuery()
                return if (rs.next()) mapRow(rs) else null
            }
        }
    }

    /**
     * 추가, 수정, 삭제하는 helper method
     * @return 변경된 행의 갯수
     */
    fun update(sql: String, vararg params: Any): Int {
        ds.connection.use { conn ->
            conn.prepareStatement(sql).use { ps ->
                setParameters(ps, params)
                return ps.executeUpdate()
            }
        }
    }

    /**
     * preparedStatement에 파라미터를 설정한다.
     */
    fun setParameters(ps: PreparedStatement, params: Array<out Any>) =
        params.forEachIndexed { i, param -> ps.setObject(i + 1, param) }
}