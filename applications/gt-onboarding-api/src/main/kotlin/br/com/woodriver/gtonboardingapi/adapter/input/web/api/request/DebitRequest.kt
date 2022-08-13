package br.com.woodriver.gtonboardingapi.adapter.input.web.api.request

import com.fasterxml.jackson.databind.PropertyNamingStrategy
import com.fasterxml.jackson.databind.annotation.JsonNaming
import java.math.BigDecimal

@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy::class)
data class DebitRequest(
    val customerId: String,
    val amount: BigDecimal,
    val processingCode: String
)