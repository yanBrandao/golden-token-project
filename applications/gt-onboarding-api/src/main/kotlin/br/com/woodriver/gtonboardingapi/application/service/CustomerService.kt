package br.com.woodriver.gtonboardingapi.application.service

import br.com.woodriver.gtonboardingapi.application.domain.Customer
import br.com.woodriver.gtonboardingapi.application.exception.CustomerAlreadyExistsException
import br.com.woodriver.gtonboardingapi.application.exception.CustomerNotFoundException
import br.com.woodriver.gtonboardingapi.application.port.input.CreateUserUseCase
import br.com.woodriver.gtonboardingapi.application.port.input.GetUserUseCase
import br.com.woodriver.gtonboardingapi.application.port.input.UpdateUserUseCase
import br.com.woodriver.gtonboardingapi.application.port.output.CustomerRepositoryPort
import br.com.woodriver.gtonboardingapi.application.port.output.PaymentRepositoryPort
import org.springframework.stereotype.Service

@Service
class CustomerService(
    val customerRepositoryPort: CustomerRepositoryPort,
    val paymentRepositoryPort: PaymentRepositoryPort
): CreateUserUseCase, GetUserUseCase, UpdateUserUseCase {
    override fun executeCreate(customer: Customer): Customer {
        try {
            customerRepositoryPort.findCustomerById(customer.customerId)
            throw CustomerAlreadyExistsException("Customer is already created")
        }catch (notFoundException: CustomerNotFoundException) {
            customer.save(customerRepositoryPort, paymentRepositoryPort)
        }
        return customer
    }

    override fun executeGet(customerId: String): Customer {
        return customerRepositoryPort.findCustomerById(customerId)
    }

    override fun executeUpdate(customerId: String, customer: Customer): Customer {
        return customer.update(customerId, customerRepositoryPort)
    }
}