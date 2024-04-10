package spring.springjdbc.user

import jdbc.Page
import jdbc.user.UserDao
import spring.springjdbc.applicationContext
import java.util.*

fun main() {
    val userDao = applicationContext.getBean(UserDao::class.java)

    print("List - pageNumber pageSize ? ")
    val scanner = Scanner(System.`in`)
    val page = Page(scanner.nextInt(), scanner.nextInt())
    scanner.close()

    val userList = userDao.list(page)
    userList.forEach(::println)
}