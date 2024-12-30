package com.gema.news_api.models

data class RegisterRequest (
    val username: String,
    val password: String,
    val email: String,
    val fullName: String

)

