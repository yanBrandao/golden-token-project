package br.com.woodriver.gtonboardingapi.adapter.out.repository.converter

import br.com.woodriver.gtonboardingapi.adapter.out.repository.entity.CustomerEntity
import br.com.woodriver.gtonboardingapi.application.domain.Customer
import br.com.woodriver.gtonboardingapi.application.util.encrypt


fun Customer.toEntity(): CustomerEntity =
    CustomerEntity(
        customerId = customerId,
        documentNumber = documentNumber,
        password = (password + documentNumber + createdAt).encrypt(),
        lastName = lastName,
        nickname = nickname,
        email = email,
        birthDate = birthDate,
        addressLine = addressLine,
        phoneEntity = CustomerEntity.PhoneEntity(
            ddd = phone.ddd,
            ddi = phone.ddi,
            number = phone.number
        ),
        name = name
    )

fun CustomerEntity.toDomain(): Customer =
    Customer(
        name = name,
        email = email,
        addressLine = addressLine,
        lastName = lastName,
        birthDate = birthDate,
        documentNumber = documentNumber,
        phone = Customer.Phone(
            ddi = phoneEntity.ddi,
            ddd = phoneEntity.ddd,
            number = phoneEntity.number
        ),
        nickname = nickname,
        customerId = customerId,
        password = password
    )