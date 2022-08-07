package br.com.woodriver.gtonboardingapi.application.port.input

import br.com.woodriver.gtonboardingapi.application.domain.Customer

interface CreateUserUseCase {

    fun executeCreate(customer: Customer): Customer
}