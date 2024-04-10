package jdbc.post

import jdbc.getDatetime
import jdbc.getValue
import java.sql.ResultSet

fun mapPost(rs: ResultSet) =
    Post(id = rs.getInt("id"), title = rs.getString("title"),
        content = rs.getValue("content", ""), userId = rs.getInt("user_id"),
        firstName = rs.getString("first_name"),
        pubDate = rs.getDatetime("pub_date"),
        lastModified = rs.getTimestamp("last_modified").toLocalDateTime())






