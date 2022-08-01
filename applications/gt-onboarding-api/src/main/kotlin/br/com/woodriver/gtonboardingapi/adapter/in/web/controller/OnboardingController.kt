package br.com.woodriver.gtonboardingapi.adapter.`in`.web.controller

import br.com.woodriver.gtonboardingapi.adapter.`in`.web.api.OnboardingAPI
import br.com.woodriver.gtonboardingapi.adapter.`in`.web.request.OnboardingNewUserRequest
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity

class OnboardingController: OnboardingAPI {
    override fun createUser(request: OnboardingNewUserRequest): ResponseEntity<Any> {
        return ResponseEntity(HttpStatus.CREATED)
    }
}