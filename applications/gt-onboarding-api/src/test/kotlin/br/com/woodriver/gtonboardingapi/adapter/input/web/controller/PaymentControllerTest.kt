package br.com.woodriver.gtonboardingapi.adapter.input.web.controller

import br.com.woodriver.gtonboardingapi.application.port.input.CreditUseCase
import br.com.woodriver.gtonboardingapi.application.port.input.DebitUseCase
import br.com.woodriver.gtonboardingapi.application.port.input.RefundUseCase
import br.com.woodriver.gtonboardingapi.utils.randomObject
import io.mockk.every
import io.mockk.mockk
import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*
import java.util.UUID

internal class PaymentControllerTest {
    private val creditUseCase: CreditUseCase = mockk()
    private val debitUseCase: DebitUseCase = mockk()
    private val refundUseCase: RefundUseCase = mockk()
    private val paymentController = PaymentController(creditUseCase, debitUseCase, refundUseCase)

    @Test
    fun makeDebit() {
        val customerId = UUID.randomUUID().toString()

        every { debitUseCase.executeDebit(any(), any()) } returns randomObject()

        paymentController.makeDebit(customerId, randomObject())
    }

    @Test
    fun makeCredit() {
        val customerId = UUID.randomUUID().toString()


        every { creditUseCase.executeCredit(any(), any()) } returns randomObject()

        paymentController.makeCredit(customerId, randomObject())
    }

    @Test
    fun refundTransaction() {
        val customerId = UUID.randomUUID().toString()

        every { refundUseCase.executeRefund(any(), any()) } returns randomObject()

        paymentController.refundTransaction(customerId, randomObject())
    }
}