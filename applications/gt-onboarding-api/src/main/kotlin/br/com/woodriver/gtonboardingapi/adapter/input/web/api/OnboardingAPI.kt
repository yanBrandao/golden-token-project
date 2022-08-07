package br.com.woodriver.gtonboardingapi.adapter.input.web.api

import br.com.woodriver.gtonboardingapi.adapter.input.web.api.request.OnboardingNewUserRequest
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping

@RequestMapping(value = ["/onboardings"])
interface OnboardingAPI {

    @PostMapping
    fun createUser(@RequestBody request: OnboardingNewUserRequest): ResponseEntity<Any>


    @GetMapping(value = ["/{customerId}"])
    fun getUser(@PathVariable customerId: String): ResponseEntity<Any>


    @PutMapping(value = ["/{customerId}"])
    fun updateUser(@PathVariable customerId: String, @RequestBody request: OnboardingNewUserRequest): ResponseEntity<Any>
}