package br.com.woodriver.gtonboardingapi.application.domain

import br.com.woodriver.gtonboardingapi.application.port.output.CustomerRepositoryPort
import br.com.woodriver.gtonboardingapi.application.port.output.PaymentRepositoryPort
import br.com.woodriver.gtonboardingapi.application.util.EMPTY
import java.math.BigDecimal.ZERO
import java.time.LocalDateTime
import java.util.*

data class Customer(
    var customerId: String = EMPTY,
    val name: String = EMPTY,
    val lastName: String = EMPTY,
    val nickname: String = EMPTY,
    val documentNumber: String = EMPTY,
    val addressLine: String = EMPTY,
    val phone: Phone = Phone(),
    val email: String = EMPTY,
    val birthDate: Date = Date(),
    val password: String = EMPTY,
    val createdAt: LocalDateTime = LocalDateTime.now()
) {
    data class Phone(
        val ddi: String = EMPTY,
        val ddd: String = EMPTY,
        val number: String = EMPTY
    )

    init {
        customerId = UUID.nameUUIDFromBytes(documentNumber.toByteArray()).toString()
    }

    fun save(
        customerRepositoryPort: CustomerRepositoryPort,
        paymentRepositoryPort: PaymentRepositoryPort) {
        paymentRepositoryPort.saveOrUpdate(
            Wallet(
                customerId = customerId,
                balance = ZERO
            )
        )
        customerRepositoryPort.saveOrUpdate(this)
    }

    fun update(customerId: String, customerRepositoryPort: CustomerRepositoryPort): Customer {
        val originalCustomer = customerRepositoryPort.findCustomerById(customerId)

        val updatedCustomer = originalCustomer.copy(
            name = name,
            lastName = lastName,
            nickname = nickname,
            addressLine =  addressLine,
            phone = phone,
            email =  email,
            birthDate = birthDate,
            password = password,
            createdAt = createdAt
        )

        customerRepositoryPort.saveOrUpdate(
            updatedCustomer
        )
        return updatedCustomer
    }
}