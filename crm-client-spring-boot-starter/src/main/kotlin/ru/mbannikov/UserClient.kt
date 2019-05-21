package ru.mbannikov

import reactor.core.publisher.Mono
import ru.mbannikov.response.UserResponse
import ru.mbannikov.response.UserEmail
import ru.mbannikov.response.UserID

interface UserClient {
    fun getUser(id: UserID): Mono<UserResponse>

    fun getUser(email: UserEmail): Mono<UserResponse>
}