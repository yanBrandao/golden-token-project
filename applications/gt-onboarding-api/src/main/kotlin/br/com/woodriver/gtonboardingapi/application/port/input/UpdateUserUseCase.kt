package br.com.woodriver.gtonboardingapi.application.port.input

import br.com.woodriver.gtonboardingapi.application.domain.Customer

interface UpdateUserUseCase {

    fun executeUpdate(customer: Customer): Customer
}