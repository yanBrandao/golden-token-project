package br.com.woodriver.gtonboardingapi.application.domain

import br.com.woodriver.gtonboardingapi.application.domain.Transaction.TransactionType.DEBIT
import br.com.woodriver.gtonboardingapi.application.exception.AlreadyRefundTransactionException
import br.com.woodriver.gtonboardingapi.application.exception.InvalidTransactionAmountException
import br.com.woodriver.gtonboardingapi.application.exception.InvalidTransactionTypeException
import br.com.woodriver.gtonboardingapi.application.exception.WithoutBalanceForOperationException
import br.com.woodriver.gtonboardingapi.application.port.output.PaymentRepositoryPort
import java.math.BigDecimal
import java.math.BigDecimal.ZERO

data class Wallet(
    val customerId: String,
    var balance: BigDecimal = ZERO
) {
    fun credit(transaction: Transaction, paymentRepositoryPort: PaymentRepositoryPort) {
        if (transaction.amount > ZERO)
            balance = balance.add(transaction.amount)
        else
            throw InvalidTransactionAmountException("Could not credit amount less then zero.")

        paymentRepositoryPort.saveOrUpdate(this)
        paymentRepositoryPort.saveOrUpdate(customerId, transaction)
    }

    fun debit(transaction: Transaction, paymentRepositoryPort: PaymentRepositoryPort) {
        if (balance.minus(transaction.amount) > ZERO)
            balance = balance.minus(transaction.amount)
        else throw WithoutBalanceForOperationException("Could not complete operation due no balance. " +
                "[currentBalance=$balance] and [Operation=${transaction.amount}]")

        paymentRepositoryPort.saveOrUpdate(this)
        paymentRepositoryPort.saveOrUpdate(customerId, transaction)
    }

    fun refund(transaction: Transaction, paymentRepositoryPort: PaymentRepositoryPort) {
        val transactionToRefund = paymentRepositoryPort.findTransactionByAuthorizationId(customerId, transaction.authorizationId)

        if (transactionToRefund.alreadyRefunded)
            throw AlreadyRefundTransactionException("Could not refund a refunded transaction.")

        when(transactionToRefund.type) {
            DEBIT -> balance = balance.plus(transactionToRefund.amount)
            else -> throw InvalidTransactionTypeException("This type cannot be refunded")
        }


        transactionToRefund.alreadyRefunded = true
        paymentRepositoryPort.saveOrUpdate(this)
        paymentRepositoryPort.saveOrUpdate(customerId, transaction)
        paymentRepositoryPort.saveOrUpdate(customerId, transactionToRefund)
    }
}