package br.com.woodriver.gtonboardingapi.adapter.out.repository

import br.com.woodriver.gtonboardingapi.adapter.out.repository.converter.toEntity
import br.com.woodriver.gtonboardingapi.adapter.out.repository.entity.CustomerEntity
import br.com.woodriver.gtonboardingapi.adapter.out.repository.entity.WalletEntity
import br.com.woodriver.gtonboardingapi.application.domain.Customer
import br.com.woodriver.gtonboardingapi.application.exception.CustomerNotFoundException
import br.com.woodriver.gtonboardingapi.utils.randomCustomer
import br.com.woodriver.gtonboardingapi.utils.randomObject
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper
import io.mockk.every
import io.mockk.justRun
import io.mockk.mockk
import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.assertThrows
import java.util.*

internal class CustomerRepositoryTest {

    private val dynamoDBMapper: DynamoDBMapper = mockk()
    private val customerRepository = CustomerRepository(dynamoDBMapper)

    @Test
    fun `Should save customer successfully`() {

        justRun { dynamoDBMapper.save<CustomerEntity>(any()) }

        customerRepository.saveOrUpdate(randomCustomer("01365201201"))
    }

    @Test
    fun `Should find customer by id successfully`() {
        val customer = randomCustomer("01365201201")

        every { dynamoDBMapper.load<CustomerEntity>(CustomerEntity::class.java, any()) } returns customer.toEntity()

        val result = customerRepository.findCustomerById("01365201201")

        assertEquals(customer.documentNumber, result.documentNumber)
    }

    @Test
    fun `Should throw exception CustomerNotFound when customer_id not exists`() {
        every { dynamoDBMapper.load<CustomerEntity>(CustomerEntity::class.java, any()) } returns null

        assertThrows<CustomerNotFoundException> {
            customerRepository.findCustomerById("01365201201")
        }
    }
}