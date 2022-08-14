package br.com.woodriver.gtonboardingapi.adapter.input.web.controller.converter

import br.com.woodriver.gtonboardingapi.adapter.input.web.api.request.*
import br.com.woodriver.gtonboardingapi.adapter.input.web.api.response.OnboardingNewUserResponse
import br.com.woodriver.gtonboardingapi.adapter.input.web.api.response.TransactionResponse
import br.com.woodriver.gtonboardingapi.application.domain.Customer
import br.com.woodriver.gtonboardingapi.application.domain.Transaction
import br.com.woodriver.gtonboardingapi.application.domain.Transaction.TransactionType.*
import br.com.woodriver.gtonboardingapi.application.domain.Wallet

fun NewCustomerRequest.toDomain() = Customer(
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

fun UpdateCustomerRequest.toDomain() = Customer(
    name = this.name,
    addressLine = this.addressLine,
    birthDate = this.birthDate,
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

fun CreditRequest.toDomain(): Transaction =
    Transaction(
        amount = this.amount,
        type = CREDIT
    )

fun DebitRequest.toDomain(): Transaction =
    Transaction(
        amount = amount,
        type = DEBIT
    )

fun RefundRequest.toDomain(): Transaction =
    Transaction(
        authorizationId = authorizationId,
        type = REFUND
    )

fun Wallet.toResponse(transaction: Transaction): TransactionResponse =
    TransactionResponse(
        customerId = customerId,
        balance = balance,
        transaction = arrayListOf(TransactionResponse.ItemTransactionResponse(
            amount = transaction.amount,
            authorizationId = transaction.authorizationId
        ))
    )