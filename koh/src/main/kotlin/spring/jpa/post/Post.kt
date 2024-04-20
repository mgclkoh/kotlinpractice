package spring.jpa.post

import jakarta.persistence.*
import jdbc.formatted
import spring.jpa.user.User
import java.time.LocalDateTime

//엔티티 생성(Post)는 primary key로 @id @generatedvalue가짐)
/* 1:N관계에서의 동작
1 부모 엔티티[User]에 자식 엔티티(Post)의 컬렉션을 속성으로 넣고
@OneToMany(mappedBy="자식(Post) 엔티티의 부모 속성명(user)")
기본으로 지연(lazy) 로딩하므로 @EntityGraph를 사용해서 필요한 엔티티만 즉시 로딩한다.
-----------------------------------------
N 자식 엔티티)(Post)에 부모[User] 오브젝트를 속성(user?)으로 넣고
@ManyToOne관계이며, @JoinColumn(name = "조인하는 컬럼명"{user-id})
기본으로 즉시(Eager) 로딩한다.
*/
@Entity
class Post {
    /*
    strategy = GenerationType.IDENTITY는 schema에 AUTO_INCREMENT썼으므로
    기본키(id)를 자동으로 증가시키기 위하여 엔티티의 기본키 자동생성 방법 지정하는것
    */
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)var id: Long = 0
    /*
    lateinit: property 선언시 동시에 초기화하지 않고,
    나중에 porperty초기화가능하게 함.  WHY?
    -> spirng jpa엔티티 클래스 정의시 엔티티 porperty즉시 초기화할 수 없는 경우 많이 생기므로,
    */
    lateinit var title: String
    lateinit var content: String
    @ManyToOne @JoinColumn(name = "user_id") lateinit var  user: User
    lateinit var pubDate: LocalDateTime
    lateinit var lastModified: LocalDateTime
    //Post를 toString() method이용해 문자열로 변환
    override fun toString(): String =
        //Post객체의 속성
        "Post(id=$id, title= '$title', content= '$content',pubDate=${pubDate.formatted}, " +
                "lastModified=${lastModified.formatted}, user=$user"
}