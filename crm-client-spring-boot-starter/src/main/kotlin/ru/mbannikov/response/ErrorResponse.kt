package ru.mbannikov.response

data class ErrorResponse(
    val code: Int,
    val message: String
)