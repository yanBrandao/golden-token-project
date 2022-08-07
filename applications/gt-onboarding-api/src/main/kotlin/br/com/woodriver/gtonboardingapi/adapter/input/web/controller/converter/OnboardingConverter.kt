package br.com.woodriver.gtonboardingapi.adapter.input.web.controller.converter

import br.com.woodriver.gtonboardingapi.adapter.input.web.api.request.OnboardingNewUserRequest
import br.com.woodriver.gtonboardingapi.adapter.input.web.api.response.OnboardingNewUserResponse
import br.com.woodriver.gtonboardingapi.application.domain.Customer

fun OnboardingNewUserRequest.toDomain() = Customer(
    name = this.name,
    addressLine = this.addressLine,
    birthDate = this.birthDate,
    documentNumber = this.documentNumber,
    email = this.email,
    nickname = this.nickname,
    lastName = this.lastName,
    password = this.password,
    phone = Customer.Phone(
        ddd = this.phoneRequest.ddd,
        ddi = this.phoneRequest.ddi,
        number = this.phoneRequest.number
    )
)

fun Customer.toResponse(): OnboardingNewUserResponse =
    OnboardingNewUserResponse(
        customerId = customerId,
        name = this.name,
        addressLine = this.addressLine,
        birthDate = this.birthDate,
        documentNumber = this.documentNumber,
        email = this.email,
        nickname = this.nickname,
        lastName = this.lastName,
        phone = OnboardingNewUserResponse.Phone(
            ddd = this.phone.ddd,
            ddi = this.phone.ddi,
            number = this.phone.number
        )
    )