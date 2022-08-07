package br.com.woodriver.gtonboardingapi.adapter.input.web.controller

import br.com.woodriver.gtonboardingapi.adapter.input.web.api.OnboardingAPI
import br.com.woodriver.gtonboardingapi.adapter.input.web.api.request.OnboardingNewUserRequest
import br.com.woodriver.gtonboardingapi.adapter.input.web.controller.converter.toDomain
import br.com.woodriver.gtonboardingapi.adapter.input.web.controller.converter.toResponse
import br.com.woodriver.gtonboardingapi.application.domain.Customer
import br.com.woodriver.gtonboardingapi.application.port.input.CreateUserUseCase
import br.com.woodriver.gtonboardingapi.application.port.input.GetUserUseCase
import br.com.woodriver.gtonboardingapi.application.port.input.UpdateUserUseCase
import org.springframework.http.HttpStatus.CREATED
import org.springframework.http.HttpStatus.OK
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.RestController

@RestController
class OnboardingController(
    val createUserUseCase: CreateUserUseCase,
    val getUserUseCase: GetUserUseCase,
    val updateUserUseCase: UpdateUserUseCase
): OnboardingAPI {
    override fun createUser(request: OnboardingNewUserRequest): ResponseEntity<Any> {
        val result = createUserUseCase.executeCreate(request.toDomain())
        return ResponseEntity(result.toResponse(), CREATED)
    }

    override fun getUser(customerId: String): ResponseEntity<Any> {
        val result  = getUserUseCase.executeGet(customerId)
        return ResponseEntity(result.toResponse(), OK)
    }

    override fun updateUser(customerId: String, request: OnboardingNewUserRequest): ResponseEntity<Any> {
        val result = updateUserUseCase.executeUpdate(request.toDomain())
        return ResponseEntity(result.toResponse(), OK)
    }
}