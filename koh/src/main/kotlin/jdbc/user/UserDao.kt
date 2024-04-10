package jdbc.user


import jdbc.Page

interface UserDao {
    fun list(page: Page): List<User2>

    fun getById(id: Int): User2?

    fun getByUsername(username: String): User2?

    fun create(user: User2): User2?

    fun changePassword(id: Int, password: String): Int

    fun deleteById(id: Int): Int
}