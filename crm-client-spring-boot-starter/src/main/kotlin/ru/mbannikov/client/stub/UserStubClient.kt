package ru.mbannikov.client.stub

import reactor.core.publisher.Mono
import reactor.core.publisher.toMono

import ru.mbannikov.UserClient
import ru.mbannikov.response.*

class UserStubClient : UserClient {
    override fun getUser(id: UserID): Mono<UserResponse> = UserResponse(
        id = id,
        firstName = "John",
        lastName = "White",
        email = "test@email.zz".toUserEmail(),
        age = 27
    ).toMono()


    override fun getUser(email: UserEmail): Mono<UserResponse> = UserResponse(
        id = 1.toUserID(),
        firstName = "John",
        lastName = "White",
        email = email,
        age = 27
    ).toMono()
}