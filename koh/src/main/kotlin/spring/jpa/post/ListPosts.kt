package spring.jpa.post

import org.slf4j.LoggerFactory
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Sort
import spring.jpa.applicationContext
import java.util.*

fun main() {
    val postRepository = applicationContext.getBean(PostRepository::class.java)
    val log =LoggerFactory.getLogger({}.javaClass)

    print("List Posts - pageNumber(0~) pageSize ? ")
    val pageable = Scanner(System.`in`).use{
        //jpa에서 대량의 데이터를 나누어 각각의 페이지로 보이기 위한 PageRequest.of method
        PageRequest.of(it.nextInt(), it.nextInt(), //pageNumber, pageSize순
            Sort.by(Sort.Direction.DESC, "id"))  //id를 기준으로 내림차순 정렬
    }
    /*페이지네이션된 Post목록 조회 후 출력*/
    //pageable객체로 조회할 페이지,크기 설정 후 Page객체에 wrap되어 반환
    val page: Page<Post> = postRepository.findAll(pageable)
    page.forEach { post -> //반환한 각 post 반복해 가져와 정보 출력
        println(
            "id=${post.id}, title=${post.title}, user(id=${post.user.id}," +
                    "username=${post.user.username})")
    }
    //반환한 페이지 정보를 로그로 출력
    log.info("isFirst={}, isLast{}, totalElements={}, totalPages={}",
        page.isFirst, page.isLast, page.totalElements, page.totalPages)

    }
