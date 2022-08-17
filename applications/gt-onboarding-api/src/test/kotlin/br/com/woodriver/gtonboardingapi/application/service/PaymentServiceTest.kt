package br.com.woodriver.gtonboardingapi.application.service

import br.com.woodriver.gtonboardingapi.application.domain.Transaction
import br.com.woodriver.gtonboardingapi.application.domain.Transaction.TransactionType.*
import br.com.woodriver.gtonboardingapi.application.domain.Wallet
import br.com.woodriver.gtonboardingapi.application.exception.AlreadyRefundTransactionException
import br.com.woodriver.gtonboardingapi.application.exception.InvalidTransactionAmountException
import br.com.woodriver.gtonboardingapi.application.exception.InvalidTransactionTypeException
import br.com.woodriver.gtonboardingapi.application.exception.WithoutBalanceForOperationException
import br.com.woodriver.gtonboardingapi.application.port.output.PaymentRepositoryPort
import br.com.woodriver.gtonboardingapi.utils.randomObject
import io.mockk.every
import io.mockk.justRun
import io.mockk.mockk
import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.assertThrows
import java.math.BigDecimal.*
import java.util.UUID

internal class PaymentServiceTest {

    private val paymentRepositoryPort: PaymentRepositoryPort = mockk()
    private val paymentService: PaymentService = PaymentService(paymentRepositoryPort)

    @Test
    fun `Should not make a credit operation with Zero amount`() {
        val wallet = randomObject<Wallet>()
        val transaction = Transaction(
            amount = ZERO,
            type = Transaction.TransactionType.CREDIT
        )

        every { paymentRepositoryPort.findWalletByCustomerId(any()) } returns wallet

        justRun { paymentRepositoryPort.saveOrUpdate(any(), any()) }
        justRun { paymentRepositoryPort.saveOrUpdate(any()) }

        assertThrows<InvalidTransactionAmountException>{ paymentService.executeCredit(randomObject(), transaction) }

    }

    @Test
    fun `Should make a credit operation successfully`() {
        val wallet = randomObject<Wallet>()
        val initialBalance = wallet.balance
        val transaction = Transaction(
            amount = ONE,
            type = Transaction.TransactionType.CREDIT
        )

        every { paymentRepositoryPort.findWalletByCustomerId(any()) } returns wallet

        justRun { paymentRepositoryPort.saveOrUpdate(any(), any()) }
        justRun { paymentRepositoryPort.saveOrUpdate(any()) }

        val result = paymentService.executeCredit(randomObject(), transaction)

        assertEquals(result.balance, initialBalance.plus(ONE))
    }

    @Test
    fun `Should make a debit operation successfully`() {
        val wallet = randomObject<Wallet>()
        wallet.balance = wallet.balance.plus(TEN)

        val initialBalance = wallet.balance

        val transaction = Transaction(
            amount = ONE,
            type = DEBIT
        )

        every { paymentRepositoryPort.findWalletByCustomerId(any()) } returns wallet

        justRun { paymentRepositoryPort.saveOrUpdate(any(), any()) }
        justRun { paymentRepositoryPort.saveOrUpdate(any()) }

        val result = paymentService.executeDebit(randomObject(), transaction)

        assertEquals(result.balance, initialBalance.minus(ONE))
    }

    @Test
    fun `Should not make a debit operation when doesn't have balance`() {
        val wallet = randomObject<Wallet>()

        val initialBalance = wallet.balance

        val transaction = Transaction(
            amount = TEN,
            type = DEBIT
        )

        every { paymentRepositoryPort.findWalletByCustomerId(any()) } returns wallet

        justRun { paymentRepositoryPort.saveOrUpdate(any(), any()) }
        justRun { paymentRepositoryPort.saveOrUpdate(any()) }

        assertThrows<WithoutBalanceForOperationException> { paymentService.executeDebit(randomObject(), transaction) }

    }

    @Test
    fun `Should make a refund operation successfully`() {
        val authorizationId = UUID.randomUUID().toString()

        val transactionToRefund = Transaction(
            amount = ONE,
            type = DEBIT,
            alreadyRefunded = false
        )


        val wallet = randomObject<Wallet>()
        wallet.balance = wallet.balance.plus(TEN)

        val initialBalance = wallet.balance

        val transaction = Transaction(
            authorizationId = authorizationId,
            type = REFUND
        )

        every { paymentRepositoryPort.findWalletByCustomerId(any()) } returns wallet
        every { paymentRepositoryPort.findTransactionByAuthorizationId(any(), any()) } returns transactionToRefund

        justRun { paymentRepositoryPort.saveOrUpdate(any(), any()) }
        justRun { paymentRepositoryPort.saveOrUpdate(any()) }

        val result = paymentService.executeRefund(randomObject(), transaction)

        assertEquals(result.balance, initialBalance.plus(ONE))
    }

    @Test
    fun `Should not make a refund operation when transaction type is different of debit`() {
        val authorizationId = UUID.randomUUID().toString()

        val transactionToRefund = Transaction(
            amount = ONE,
            type = CREDIT,
            alreadyRefunded = false
        )


        val wallet = randomObject<Wallet>()
        wallet.balance = wallet.balance.plus(TEN)

        val initialBalance = wallet.balance

        val transaction = Transaction(
            authorizationId = authorizationId,
            type = REFUND
        )

        every { paymentRepositoryPort.findWalletByCustomerId(any()) } returns wallet
        every { paymentRepositoryPort.findTransactionByAuthorizationId(any(), any()) } returns transactionToRefund

        justRun { paymentRepositoryPort.saveOrUpdate(any(), any()) }
        justRun { paymentRepositoryPort.saveOrUpdate(any()) }

        assertThrows<InvalidTransactionTypeException> { paymentService.executeRefund(randomObject(), transaction) }
    }

    @Test
    fun `Should not make a refund operation when transaction is already refunded`() {
        val authorizationId = UUID.randomUUID().toString()

        val transactionToRefund = Transaction(
            amount = ONE,
            type = CREDIT,
            alreadyRefunded = true
        )


        val wallet = randomObject<Wallet>()
        wallet.balance = wallet.balance.plus(TEN)

        val initialBalance = wallet.balance

        val transaction = Transaction(
            authorizationId = authorizationId,
            type = REFUND
        )

        every { paymentRepositoryPort.findWalletByCustomerId(any()) } returns wallet
        every { paymentRepositoryPort.findTransactionByAuthorizationId(any(), any()) } returns transactionToRefund

        justRun { paymentRepositoryPort.saveOrUpdate(any(), any()) }
        justRun { paymentRepositoryPort.saveOrUpdate(any()) }

        assertThrows<AlreadyRefundTransactionException> { paymentService.executeRefund(randomObject(), transaction) }
    }
}