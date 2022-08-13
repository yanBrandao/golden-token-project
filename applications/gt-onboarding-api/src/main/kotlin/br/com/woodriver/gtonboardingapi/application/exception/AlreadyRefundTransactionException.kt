package br.com.woodriver.gtonboardingapi.application.exception

data class AlreadyRefundTransactionException(
    override val message: String
): RuntimeException(message)