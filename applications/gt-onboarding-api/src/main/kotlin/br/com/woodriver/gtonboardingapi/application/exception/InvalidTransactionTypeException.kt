package br.com.woodriver.gtonboardingapi.application.exception

data class InvalidTransactionTypeException(
    override val message: String
): RuntimeException(message)