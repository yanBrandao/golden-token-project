package br.com.woodriver.gtonboardingapi.adapter.input.web.api.request

import com.fasterxml.jackson.databind.PropertyNamingStrategy
import com.fasterxml.jackson.databind.annotation.JsonNaming
import java.util.*

@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy::class)
data class UpdateCustomerRequest(
    val name: String,
    val lastName: String,
    val nickname: String,
    val addressLine: String,
    val phoneRequest: PhoneRequest,
    val email: String,
    val birthDate: Date,
    val password: String
) {
    data class PhoneRequest(
        val ddi: String,
        val ddd: String,
        val number: String
    )
}