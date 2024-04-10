package spring.springcore.beanfactory

class UserDao {
    init {  //instance생성시마다 출력
        println("userDao created.")
    }

    fun listUsers() {
        println("list users.")
    }
}

class PostDao {
    init {
        println("postDao created.")
    }

    fun listPosts() {
        println("list posts.")
    }


}