package br.com.woodriver.gtonboardingapi.application.port.input

import br.com.woodriver.gtonboardingapi.application.domain.Customer

interface UpdateUserUseCase {

    fun executeUpdate(customerId: String, customer: Customer): Customer
}