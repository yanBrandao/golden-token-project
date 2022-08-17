package br.com.woodriver.gtonboardingapi.adapter.input.web.controller.converter

import br.com.woodriver.gtonboardingapi.adapter.input.web.api.request.*
import br.com.woodriver.gtonboardingapi.adapter.out.repository.converter.toDomain
import br.com.woodriver.gtonboardingapi.adapter.out.repository.converter.toEntity
import br.com.woodriver.gtonboardingapi.adapter.out.repository.entity.CustomerEntity
import br.com.woodriver.gtonboardingapi.application.domain.Transaction
import br.com.woodriver.gtonboardingapi.application.domain.Transaction.TransactionType.*
import br.com.woodriver.gtonboardingapi.application.domain.Wallet
import br.com.woodriver.gtonboardingapi.utils.randomCustomer
import br.com.woodriver.gtonboardingapi.utils.randomObject
import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*
import java.util.UUID

internal class OnboardingConverterKtTest {

    @Test
    fun `Should convert new customer request to domain`() {
        val newCustomerRequest: NewCustomerRequest = randomObject()

        val result = newCustomerRequest.toDomain()

        assertEquals(newCustomerRequest.name, result.name)
    }

    @Test
    fun `Should convert update customer request to domain`() {
        val updateCustomerRequest: UpdateCustomerRequest = randomObject()

        val result = updateCustomerRequest.toDomain()

        assertEquals(updateCustomerRequest.name, result.name)
    }

    @Test
    fun `Should convert customer domain to response`() {
        val customer = randomCustomer(UUID.randomUUID().toString())

        val response = customer.toResponse()

        assertEquals(customer.name, response.name)
    }

    @Test
    fun `Should convert Credit to Transaction`() {
        val credit: CreditRequest = randomObject()

        val result = credit.toDomain()

        assertEquals(CREDIT, result.type)
        assertEquals(credit.amount, result.amount)
    }


    @Test
    fun `Should convert Debit to Transaction`() {
        val debit: DebitRequest = randomObject()

        val result = debit.toDomain()

        assertEquals(DEBIT, result.type)
        assertEquals(debit.amount, result.amount)
    }

    @Test
    fun `Should convert refund to transaction`() {
        val refund : RefundRequest = randomObject()

        val result = refund.toDomain()

        assertEquals(REFUND, result.type)
        assertEquals(refund.authorizationId, result.authorizationId)
    }

    @Test
    fun `Should convert a wallet to TransactionResponse`() {
        val wallet = randomObject<Wallet>()

        val result = wallet.toResponse(randomObject())

        assertEquals(wallet.balance, result.balance)
        assertEquals(1, result.transaction.size)
    }

    @Test
    fun `Should convert a customer to Entity`() {
        val customer = randomCustomer("01365201201")

        val result = customer.toEntity()

        assertEquals(customer.name, result.name)
    }

    @Test
    fun `Should convert a customerEntity to domain`() {
        val customer: CustomerEntity = randomObject()

        val result = customer.toDomain()

        assertEquals(customer.name, result.name)
    }


}

