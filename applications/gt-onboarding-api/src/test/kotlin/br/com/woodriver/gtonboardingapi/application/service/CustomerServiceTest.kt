package br.com.woodriver.gtonboardingapi.application.service

import br.com.woodriver.gtonboardingapi.application.domain.Customer
import br.com.woodriver.gtonboardingapi.application.exception.CustomerAlreadyExistsException
import br.com.woodriver.gtonboardingapi.application.exception.CustomerNotFoundException
import br.com.woodriver.gtonboardingapi.application.port.output.CustomerRepositoryPort
import br.com.woodriver.gtonboardingapi.application.port.output.PaymentRepositoryPort
import br.com.woodriver.gtonboardingapi.utils.randomCustomer
import br.com.woodriver.gtonboardingapi.utils.randomObject
import io.mockk.*
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import java.util.UUID

internal class CustomerServiceTest {

    private val customerRepositoryPort = mockk<CustomerRepositoryPort>()
    private val paymentRepositoryPort = mockk<PaymentRepositoryPort>()
    private val customerService = CustomerService(customerRepositoryPort, paymentRepositoryPort)

    @BeforeEach
    fun setUp() {

    }

    @Test
    fun `Should create a new customer`() {
        val customer: Customer = randomObject()

        every {
            customerRepositoryPort.findCustomerById(any())
        } throws CustomerNotFoundException("Could not find customer with id")

        justRun { customerRepositoryPort.saveOrUpdate(any()) }
        justRun { paymentRepositoryPort.saveOrUpdate(any()) }

        val response = customerService.executeCreate(customer)

        Assertions.assertEquals(customer.customerId, response.customerId)
    }

    @Test
    fun `Should throw customer already exists`() {
        val customer: Customer = randomObject()

        every {
            customerRepositoryPort.findCustomerById(any())
        } returns  customer

        justRun { customerRepositoryPort.saveOrUpdate(any()) }
        justRun { paymentRepositoryPort.saveOrUpdate(any()) }

        assertThrows<CustomerAlreadyExistsException> { customerService.executeCreate(customer) }
    }

    @Test
    fun `Should find a existing customer`() {
        val documentNumber: String = "01365201201"
        val customer = randomCustomer(documentNumber)

        every { customerRepositoryPort.findCustomerById(any()) } returns customer

        val result = customerService.executeGet(customer.customerId)

        Assertions.assertEquals(customer.customerId, result.customerId)
    }

    @Test
    fun executeUpdate() {
    }
}