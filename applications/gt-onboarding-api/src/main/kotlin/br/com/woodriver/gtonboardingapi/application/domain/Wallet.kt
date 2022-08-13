package br.com.woodriver.gtonboardingapi.application.domain

import br.com.woodriver.gtonboardingapi.application.exception.AlreadyRefundTransactionException
import br.com.woodriver.gtonboardingapi.application.exception.WithoutBalanceForOperationException
import br.com.woodriver.gtonboardingapi.application.port.output.PaymentRepositoryPort
import java.math.BigDecimal

data class Wallet(
    val customerId: String,
    var balance: BigDecimal = BigDecimal.ZERO
) {
    fun credit(transaction: Transaction, paymentRepositoryPort: PaymentRepositoryPort) {
        balance = balance.add(transaction.amount)

        paymentRepositoryPort.saveOrUpdate(this)
        paymentRepositoryPort.saveOrUpdate(customerId, transaction)
    }

    fun debit(transaction: Transaction, paymentRepositoryPort: PaymentRepositoryPort) {
        if (balance.minus(transaction.amount) > BigDecimal.ZERO)
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

        balance = balance.plus(transactionToRefund.amount)

        transactionToRefund.alreadyRefunded = true
        paymentRepositoryPort.saveOrUpdate(this)
        paymentRepositoryPort.saveOrUpdate(customerId, transaction)
        paymentRepositoryPort.saveOrUpdate(customerId, transactionToRefund)
    }
}