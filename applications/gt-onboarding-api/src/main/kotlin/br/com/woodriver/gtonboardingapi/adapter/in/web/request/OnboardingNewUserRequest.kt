package br.com.woodriver.gtonboardingapi.adapter.`in`.web.request

import java.util.*


data class OnboardingNewUserRequest(
    val name: String,
    val birthDate: Date,
    val password: String
)