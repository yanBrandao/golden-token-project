package br.com.woodriver.gtonboardingapi.adapter.input.web.controller

import br.com.woodriver.gtonboardingapi.adapter.input.web.api.request.NewCustomerRequest
import br.com.woodriver.gtonboardingapi.application.port.input.CreateUserUseCase
import br.com.woodriver.gtonboardingapi.application.port.input.GetUserUseCase
import br.com.woodriver.gtonboardingapi.application.port.input.UpdateUserUseCase
import br.com.woodriver.gtonboardingapi.utils.randomCustomer
import br.com.woodriver.gtonboardingapi.utils.randomObject
import io.mockk.every
import io.mockk.mockk
import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*
import java.util.UUID

internal class CustomerControllerTest {
    private val createUserUseCase: CreateUserUseCase = mockk()
    private val getUserUseCase: GetUserUseCase = mockk()
    private val updateUserUseCase: UpdateUserUseCase = mockk()

    private val customerController = CustomerController(createUserUseCase, getUserUseCase, updateUserUseCase)

    @Test
    fun createCustomer() {
        val request: NewCustomerRequest = randomObject()

        every { createUserUseCase.executeCreate(any()) } returns randomObject()

        customerController.createCustomer(request)
    }

    @Test
    fun getCustomer() {
        val documentNumber = "01365201201"
        val customer = randomCustomer(documentNumber)

        every { getUserUseCase.executeGet(any()) } returns customer

        customerController.getCustomer(customer.customerId)
    }

    @Test
    fun updateCustomer() {
        val documentNumber = "01365201201"
        val customer = randomCustomer(documentNumber)

        every { updateUserUseCase.executeUpdate(any(), any()) } returns randomCustomer(documentNumber)

        customerController.updateCustomer(customerId = customer.customerId, randomObject())
    }
}