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
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.springframework.util.Assert
import java.util.UUID

internal class CustomerServiceTest {

    private val customerRepositoryPort = mockk<CustomerRepositoryPort>()
    private val paymentRepositoryPort = mockk<PaymentRepositoryPort>()
    private val customerService = CustomerService(customerRepositoryPort, paymentRepositoryPort)

    @Test
    fun `Should create a new customer`() {
        val customer: Customer = randomObject()

        every {
            customerRepositoryPort.findCustomerById(any())
        } throws CustomerNotFoundException("Could not find customer with id")

        justRun { customerRepositoryPort.saveOrUpdate(any()) }
        justRun { paymentRepositoryPort.saveOrUpdate(any()) }

        val response = customerService.executeCreate(customer)

        assertEquals(customer.customerId, response.customerId)
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

        assertEquals(customer.customerId, result.customerId)
    }

    @Test
    fun `Should update personal data in existing customer`() {
        val customer = randomCustomer("01365201201")

        every { customerRepositoryPort.findCustomerById(any()) } returns randomCustomer("01365201201")
        justRun { customerRepositoryPort.saveOrUpdate(any()) }

        val updatedCustomer = customerService.executeUpdate(customer.customerId, customer)

        assertEquals(customer.name, updatedCustomer.name)
        assertEquals(customer.phone.ddd, updatedCustomer.phone.ddd)
        assertEquals(customer.phone.ddi, updatedCustomer.phone.ddi)
        assertEquals(customer.phone.number, updatedCustomer.phone.number)

        assertEquals(customer.lastName, updatedCustomer.lastName)
        assertEquals(customer.nickname, updatedCustomer.nickname)
        assertEquals(customer.documentNumber, updatedCustomer.documentNumber)
        assertEquals(customer.addressLine, updatedCustomer.addressLine)
        assertEquals(customer.email, updatedCustomer.email)
        assertEquals(customer.birthDate, updatedCustomer.birthDate)
        assertEquals(customer.password, updatedCustomer.password)
        assertEquals(customer.createdAt, updatedCustomer.createdAt)
    }
}