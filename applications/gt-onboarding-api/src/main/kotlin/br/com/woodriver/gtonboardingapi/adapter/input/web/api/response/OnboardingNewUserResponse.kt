package br.com.woodriver.gtonboardingapi.adapter.input.web.api.response

import com.fasterxml.jackson.databind.PropertyNamingStrategy
import com.fasterxml.jackson.databind.annotation.JsonNaming
import java.util.*

@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy::class)
data class OnboardingNewUserResponse(
    val customerId: String,
    val name: String,
    val lastName: String,
    val nickname: String,
    val documentNumber: String,
    val addressLine: String,
    val phone: Phone,
    val email: String,
    val birthDate: Date
) {
    data class Phone(
        val ddi: String,
        val ddd: String,
        val number: String
    )
}