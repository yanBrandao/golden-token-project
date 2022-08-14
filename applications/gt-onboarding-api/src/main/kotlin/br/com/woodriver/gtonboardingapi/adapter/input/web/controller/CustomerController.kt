package br.com.woodriver.gtonboardingapi.adapter.input.web.controller

import br.com.woodriver.gtonboardingapi.adapter.input.web.api.CustomerAPI
import br.com.woodriver.gtonboardingapi.adapter.input.web.api.request.NewCustomerRequest
import br.com.woodriver.gtonboardingapi.adapter.input.web.api.request.UpdateCustomerRequest
import br.com.woodriver.gtonboardingapi.adapter.input.web.controller.converter.toDomain
import br.com.woodriver.gtonboardingapi.adapter.input.web.controller.converter.toResponse
import br.com.woodriver.gtonboardingapi.application.port.input.CreateUserUseCase
import br.com.woodriver.gtonboardingapi.application.port.input.GetUserUseCase
import br.com.woodriver.gtonboardingapi.application.port.input.UpdateUserUseCase
import org.springframework.http.HttpStatus.CREATED
import org.springframework.http.HttpStatus.OK
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.RestController

@RestController
class CustomerController(
    val createUserUseCase: CreateUserUseCase,
    val getUserUseCase: GetUserUseCase,
    val updateUserUseCase: UpdateUserUseCase
): CustomerAPI {
    override fun createCustomer(request: NewCustomerRequest): ResponseEntity<Any> {
        val result = createUserUseCase.executeCreate(request.toDomain())
        return ResponseEntity(result.toResponse(), CREATED)
    }

    override fun getCustomer(customerId: String): ResponseEntity<Any> {
        val result  = getUserUseCase.executeGet(customerId)
        return ResponseEntity(result.toResponse(), OK)
    }

    override fun updateCustomer(customerId: String, request: UpdateCustomerRequest): ResponseEntity<Any> {
        val result = updateUserUseCase.executeUpdate(customerId, request.toDomain())
        return ResponseEntity(result.toResponse(), OK)
    }
}