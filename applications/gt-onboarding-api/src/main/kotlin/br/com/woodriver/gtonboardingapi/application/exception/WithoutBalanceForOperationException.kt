package br.com.woodriver.gtonboardingapi.application.exception

data class WithoutBalanceForOperationException(
    override val message: String
): RuntimeException(message)