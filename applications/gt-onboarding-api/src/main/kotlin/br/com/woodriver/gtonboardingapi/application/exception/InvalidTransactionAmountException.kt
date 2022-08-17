package br.com.woodriver.gtonboardingapi.application.exception

data class InvalidTransactionAmountException(
    override val message: String
): RuntimeException(message)