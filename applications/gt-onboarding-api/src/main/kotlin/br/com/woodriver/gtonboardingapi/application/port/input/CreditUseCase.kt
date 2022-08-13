package br.com.woodriver.gtonboardingapi.application.port.input

import br.com.woodriver.gtonboardingapi.application.domain.Transaction
import br.com.woodriver.gtonboardingapi.application.domain.Wallet

interface CreditUseCase {

    fun executeCredit(customerId: String, transaction: Transaction): Wallet
}