package br.com.woodriver.gtonboardingapi.application.port.output

import br.com.woodriver.gtonboardingapi.application.domain.Customer

interface CustomerRepositoryPort {

    fun saveOrUpdate(customer: Customer): Customer

    fun findCustomerById(customerId: String): Customer

}