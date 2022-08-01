package br.com.woodriver.gtonboardingapi.adapter.`in`.web.api

import br.com.woodriver.gtonboardingapi.adapter.`in`.web.request.OnboardingNewUserRequest
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RestController

@RestController(value = "/onboardings")
interface OnboardingAPI {

    @PostMapping
    fun createUser(request: OnboardingNewUserRequest): ResponseEntity<Any>
}