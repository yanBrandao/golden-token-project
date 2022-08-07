package br.com.woodriver.gtonboardingapi.application.domain

import java.math.BigDecimal

data class Wallet(
    val customerId: String,
    val balance: BigDecimal
)