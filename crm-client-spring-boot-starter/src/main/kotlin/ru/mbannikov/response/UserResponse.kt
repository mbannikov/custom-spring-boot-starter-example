package ru.mbannikov.response

inline class UserID(val value: Int)
inline class UserEmail(val value: String)

data class UserResponse(
    val id: UserID,
    val firstName: String,
    val lastName: String,
    val email: UserEmail,
    val age: Int
)

fun Int.toUserID() = UserID(this)
fun String.toUserEmail() = UserEmail(this)
