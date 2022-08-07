package br.com.woodriver.gtonboardingapi.application.port.input

import br.com.woodriver.gtonboardingapi.application.domain.Customer

interface GetUserUseCase {

    fun executeGet(customerId: String): Customer
}