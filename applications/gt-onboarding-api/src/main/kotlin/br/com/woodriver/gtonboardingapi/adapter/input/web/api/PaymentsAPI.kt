package br.com.woodriver.gtonboardingapi.adapter.input.web.api

import br.com.woodriver.gtonboardingapi.adapter.input.web.api.request.CreditRequest
import br.com.woodriver.gtonboardingapi.adapter.input.web.api.request.DebitRequest
import br.com.woodriver.gtonboardingapi.adapter.input.web.api.request.OnboardingNewUserRequest
import br.com.woodriver.gtonboardingapi.adapter.input.web.api.request.RefundRequest
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping

@RequestMapping(value = ["/transactions"])
interface PaymentsAPI {

    @PostMapping(value = ["/{customerId}/debit"])
    fun makeDebit(@PathVariable customerId: String, @RequestBody request: DebitRequest): ResponseEntity<Any>


    @PostMapping(value = ["/{customerId}/credit"])
    fun makeCredit(@PathVariable customerId: String, @RequestBody request: CreditRequest): ResponseEntity<Any>


    @PostMapping(value = ["/{customerId}/refund"])
    fun refundTransaction(@PathVariable customerId: String, @RequestBody request: RefundRequest): ResponseEntity<Any>
}