package br.com.woodriver.gtonboardingapi.application.exception

data class CustomerNotFoundException(
    override val message: String
): RuntimeException(message)