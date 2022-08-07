package br.com.woodriver.gtonboardingapi.application.exception

data class CustomerAlreadyExistsException(
    override val message: String
): RuntimeException(message)