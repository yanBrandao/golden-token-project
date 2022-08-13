package br.com.woodriver.gtonboardingapi.adapter.input.web.controller

import br.com.woodriver.gtonboardingapi.adapter.input.web.api.PaymentsAPI
import br.com.woodriver.gtonboardingapi.adapter.input.web.api.request.CreditRequest
import br.com.woodriver.gtonboardingapi.adapter.input.web.api.request.DebitRequest
import br.com.woodriver.gtonboardingapi.adapter.input.web.api.request.RefundRequest
import br.com.woodriver.gtonboardingapi.adapter.input.web.controller.converter.toDomain
import br.com.woodriver.gtonboardingapi.adapter.input.web.controller.converter.toResponse
import br.com.woodriver.gtonboardingapi.application.port.input.CreditUseCase
import br.com.woodriver.gtonboardingapi.application.port.input.DebitUseCase
import br.com.woodriver.gtonboardingapi.application.port.input.RefundUseCase
import br.com.woodriver.gtonboardingapi.application.util.toJsonString
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.RestController

@RestController
class PaymentController(
    private val creditUseCase: CreditUseCase,
    private val debitUseCase: DebitUseCase,
    private val refundUseCase: RefundUseCase
): PaymentsAPI {
    override fun makeDebit(customerId: String, request: DebitRequest): ResponseEntity<Any> {
        val transaction = request.toDomain()
        val wallet = debitUseCase.executeDebit(customerId, transaction)

        return ResponseEntity(wallet.toResponse(transaction).toJsonString(), HttpStatus.CREATED)
    }

    override fun makeCredit(customerId: String, request: CreditRequest): ResponseEntity<Any> {
        val transaction = request.toDomain()
        val wallet = creditUseCase.executeCredit(customerId, transaction)

        return ResponseEntity(wallet.toResponse(transaction).toJsonString(), HttpStatus.CREATED)
    }

    override fun refundTransaction(customerId: String, request: RefundRequest): ResponseEntity<Any> {
        val transaction = request.toDomain()
        val wallet = refundUseCase.executeRefund(customerId, transaction)

        return ResponseEntity(wallet.toResponse(transaction).toJsonString(), HttpStatus.CREATED)
    }
}