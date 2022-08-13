package br.com.woodriver.gtonboardingapi.adapter.input.web.api.request

import com.fasterxml.jackson.databind.PropertyNamingStrategy
import com.fasterxml.jackson.databind.annotation.JsonNaming
import java.math.BigDecimal

@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy::class)
data class CreditRequest(
    val amount: BigDecimal,
    val customerId: String,
    val processingCode: String
)
