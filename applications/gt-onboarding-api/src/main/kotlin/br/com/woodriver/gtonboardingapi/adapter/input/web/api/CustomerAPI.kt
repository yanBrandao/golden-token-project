package br.com.woodriver.gtonboardingapi.adapter.input.web.api

import br.com.woodriver.gtonboardingapi.adapter.input.web.api.request.NewCustomerRequest
import br.com.woodriver.gtonboardingapi.adapter.input.web.api.request.UpdateCustomerRequest
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RequestMapping(value = ["/customers"])
interface CustomerAPI {

    @PostMapping
    fun createCustomer(@RequestBody request: NewCustomerRequest): ResponseEntity<Any>


    @GetMapping(value = ["/{customerId}"])
    fun getCustomer(@PathVariable customerId: String): ResponseEntity<Any>


    @PutMapping(value = ["/{customerId}"])
    fun updateCustomer(@PathVariable customerId: String, @RequestBody request: UpdateCustomerRequest): ResponseEntity<Any>
}