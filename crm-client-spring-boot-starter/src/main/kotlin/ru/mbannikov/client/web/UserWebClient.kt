package ru.mbannikov.client.web

import org.springframework.http.HttpMethod
import org.springframework.http.MediaType
import org.springframework.web.reactive.function.client.WebClient
import reactor.core.publisher.Mono

import ru.mbannikov.UserClient
import ru.mbannikov.exception.CrmErrorException
import ru.mbannikov.response.ErrorResponse
import ru.mbannikov.response.UserResponse
import ru.mbannikov.response.UserEmail
import ru.mbannikov.response.UserID

internal class UserWebClient(baseUrl: String) : UserClient {
    private val client = WebClient.builder()
        .baseUrl(baseUrl)
        .build()

    override fun getUser(id: UserID): Mono<UserResponse> = getUser("/user?id=${id.value}")

    override fun getUser(email: UserEmail): Mono<UserResponse> = getUser("/user?email=${email.value}")

    private fun getUser(uri: String): Mono<UserResponse> {
        return client.method(HttpMethod.GET)
            .uri(uri)
            .contentType(MediaType.APPLICATION_JSON_UTF8)
            .retrieve()
            .onStatus({ status -> status.is4xxClientError || status.is5xxServerError }) { response ->
                response
                    .bodyToMono(ErrorResponse::class.java)
                    .flatMap { Mono.error<Throwable>(CrmErrorException(it.message)) }
            }
            .onStatus({ true }) {
                Mono.error<Throwable>(CrmErrorException("Unknown error"))
            }
            .bodyToMono(UserResponse::class.java)
    }
}