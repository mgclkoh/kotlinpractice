package jdbc.user.usinghelper

import jdbc.DataSourceFactory
import jdbc.JdbcHelper
import jdbc.Page
import jdbc.user.User2
import jdbc.user.UserDao
import java.sql.ResultSet

class UserDaoImpl : UserDao {

    companion object {
        private const val LIST = "select * from user order by id desc limit ?,?"

        private const val GET_BY_ID = "select * from user where id=?"

        private const val GET_BY_USERNAME = "select * from user where username=?"

        private const val CREATE =
            "insert user(username, password, first_name) values(?, ? ,?) returning *"

        private const val CHANGE_PASSWORD = "update user set password=? where id=?"

        private const val DELETE_BY_ID = "delete from user where id=?"
    }

    private val jdbcHelper = JdbcHelper(DataSourceFactory.dataSource)

    private fun mapUser(rs: ResultSet): User2 =
        User2(id = rs.getInt("id"), username = rs.getString("username"),
            password = rs.getString("password"),
            firstName = rs.getString("first_name"),
            dateJoined = rs.getTimestamp("date_joined").toLocalDateTime())

    /**
     * 회원 목록
     */
    override fun list(page: Page): List<User2> {
        return jdbcHelper.list(LIST, ::mapUser, page.offset, page.size)
    }

    /**
     * 회원 1건 조회
     * @return 회원이 없을 경우 null
     */
    override fun getById(id: Int): User2? =
        jdbcHelper.get(GET_BY_ID, ::mapUser, id)

    /**
     * 이메일로 회원 조회
     * @return 회원이 없을 경우 null
     */
    override fun getByUsername(username: String): User2? =
        jdbcHelper.get(GET_BY_USERNAME, ::mapUser, username)

    /**
     * 회원 가입
     * @exception SQLException username이 중복일 경우
     */
    override fun create(user: User2): User2? =
        jdbcHelper.get(CREATE, ::mapUser, user.username, user.password,
            user.firstName)

    /**
     * 비밀번호 변경
     */
    override fun changePassword(id: Int, password: String) =
        jdbcHelper.update(CHANGE_PASSWORD, password, id)

    /**
     * 회원 삭제
     */
    override fun deleteById(id: Int) = jdbcHelper.update(DELETE_BY_ID, id)
}
