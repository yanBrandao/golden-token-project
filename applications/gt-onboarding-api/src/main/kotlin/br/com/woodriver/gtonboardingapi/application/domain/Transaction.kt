package br.com.woodriver.gtonboardingapi.application.domain

import java.math.BigDecimal
import java.util.*

data class Transaction(
    val authorizationId: String = UUID.randomUUID().toString(),
    val amount: BigDecimal = BigDecimal.ZERO,
    val type: TransactionType,
    val executedAt: Date = Date(),
    var alreadyRefunded: Boolean = false
) {
    enum class TransactionType {
        DEBIT, CREDIT, REFUND
    }

}