package com.example.vefa_can_beytorun_odev6.models

data class JWTData (
    val id: Long,
    val username: String,
    val email: String,
    val firstName: String,
    val lastName: String,
    val gender: String,
    val image: String,
    val token: String
)


data class JWTUser (
    val username: String,
    val password: String
)