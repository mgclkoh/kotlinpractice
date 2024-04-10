package spring.springjdbc.post

import jdbc.Page
import jdbc.post.Post
import jdbc.post.PostDao
import jdbc.toMap
import org.springframework.jdbc.core.BeanPropertyRowMapper
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate
import org.springframework.stereotype.Component

@Component
class PostDaoImpl(
    private val template: NamedParameterJdbcTemplate) : PostDao {
    
    companion object {
        const val LIST_POST =
            "select id, title, user_id, first_name, pub_date from post order by id desc limit :offset, :size"

        const val CREATE_POST =
            "insert post(title, content, user_id, first_name) values(:title, :content, :userId, :firstName)"

        private val GET_BY_ID = """
      select id, title, content, user_id, first_name, pub_date, last_modified
      from post where id=?""".trimIndent()

        private const val UPDATE =
            "update post set title=:title, content=:content where id=:id"

        private const val DELETE_BY_ID = "delete from post where id=?"

    }

    private val rowMapper = BeanPropertyRowMapper(Post::class.java)  //* resultSet의 컬럼을 bean의 property에 자동 매핑

    private val jdbcTemplate = template.jdbcTemplate

    override fun list(page: Page): List<Post> =
        template.query(LIST_POST, page.toMap, rowMapper)

    override fun getById(id: Int): Post? =
        jdbcTemplate.queryForObject(GET_BY_ID, rowMapper, id)

    override fun create(post: Post): Post =
            template.queryForObject(CREATE_POST, post.toMap, rowMapper)!!
    //Int = template.update(CREATE_POST, post.toMap)


    override fun update(post: Post): Int =
        template.update(UPDATE, post.toMap)


    override fun deleteById(id: Int): Int =
        jdbcTemplate.update(DELETE_BY_ID, id)
}
    
