package br.com.woodriver.gtonboardingapi.application.domain

import br.com.woodriver.gtonboardingapi.application.port.output.CustomerRepositoryPort
import br.com.woodriver.gtonboardingapi.application.util.EMPTY
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

    fun save(customerRepositoryPort: CustomerRepositoryPort): Customer {
        return customerRepositoryPort.saveOrUpdate(this)
    }
}