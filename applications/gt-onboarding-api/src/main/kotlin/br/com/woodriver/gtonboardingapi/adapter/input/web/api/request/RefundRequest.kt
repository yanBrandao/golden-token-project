package br.com.woodriver.gtonboardingapi.adapter.input.web.api.request

import com.fasterxml.jackson.databind.PropertyNamingStrategy
import com.fasterxml.jackson.databind.annotation.JsonNaming

@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy::class)
class RefundRequest(
    val authorizationId: String
)
