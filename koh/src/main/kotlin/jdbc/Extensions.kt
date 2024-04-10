package jdbc


import org.mindrot.jbcrypt.BCrypt
import java.sql.ResultSet
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import kotlin.reflect.full.memberProperties

/**
 * 오브젝트의 속성들을 맵으로 만드는 extension
 */
/**
 * 오브젝트의 속성들을 맵으로 만든다.
 */
val Any.toMap: Map<String, Any?>
    get() = this::class.memberProperties.associate { prop ->
        prop.name to prop.getter.call(this)
    }

/**
 * 문자열을 bcrypt hash 한다.
 */
val String.bcryptHashed: String get() = BCrypt.hashpw(this, BCrypt.gensalt())

/**
 * 날짜를 "yyyy-MM-dd HH:mm:ss"로 포맷하는 formatter
 */
val formatter: DateTimeFormatter =
    DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")

/**
 * 날짜를 formatter로 포맷한다.
 */
val LocalDateTime.formatted: String get() = this.format(formatter)

/**
 * ResultSet에서 컬럼이 없을 경우 null을 리턴
 */
inline fun <reified T> ResultSet.getValue(columnName: String,
                                          defaultValue: T): T = try {
    getObject(columnName, T::class.java)
} catch (e: Exception) {
    defaultValue
}

/**
 * Timestamp: Date 타입을 LocatDateTime 타입으로 변환
 */
fun ResultSet.getDatetime(columnName: String): LocalDateTime =
    getTimestamp(columnName).toLocalDateTime()