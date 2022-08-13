package br.com.woodriver.gtonboardingapi.adapter.out.repository.converter

import br.com.woodriver.gtonboardingapi.adapter.out.repository.entity.WalletEntity
import br.com.woodriver.gtonboardingapi.adapter.out.repository.entity.WalletEntity.Companion.BALANCE_OPERATION_TYPE
import br.com.woodriver.gtonboardingapi.application.domain.Transaction
import br.com.woodriver.gtonboardingapi.application.domain.Transaction.TransactionType.*
import br.com.woodriver.gtonboardingapi.application.domain.Wallet
import br.com.woodriver.gtonboardingapi.application.exception.InvalidTransactionTypeException
import java.math.BigDecimal


fun Wallet.toEntity(): WalletEntity =
    WalletEntity(
        customerId = customerId,
        operation = BALANCE_OPERATION_TYPE,
        balance = this.balance.toDouble()
    )

fun Transaction.toEntity(customerId: String): WalletEntity =
    WalletEntity(
        customerId = customerId,
        operation = "transaction-${authorizationId}",
        authorizationId = authorizationId,
        amount = this.amount.toDouble(),
        createdAt = executedAt,
        transactionType = type.toString(),
        alreadyRefund = alreadyRefunded
    )

fun WalletEntity.toDomain(): Wallet =
    Wallet(
        customerId = customerId,
        balance = balance.toBigDecimal()
    )

fun WalletEntity.toTransactionDomain(): Transaction =
    Transaction(
        authorizationId = authorizationId,
        amount = amount.toBigDecimal(),
        type = transactionType.toTransactionEnum(),
        alreadyRefunded = alreadyRefund
    )

fun String.toTransactionEnum(): Transaction.TransactionType =
    when(this) {
        CREDIT.name -> CREDIT
        DEBIT.name -> DEBIT
            REFUND.name -> REFUND
        else -> throw InvalidTransactionTypeException("Invalid transaction type. [type=$this]")
    }