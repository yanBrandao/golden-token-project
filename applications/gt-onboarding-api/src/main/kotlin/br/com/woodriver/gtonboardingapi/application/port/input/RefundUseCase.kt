package br.com.woodriver.gtonboardingapi.application.port.input

import br.com.woodriver.gtonboardingapi.application.domain.Transaction
import br.com.woodriver.gtonboardingapi.application.domain.Wallet

interface RefundUseCase {

    fun executeRefund(customerId: String, transaction: Transaction): Wallet
}