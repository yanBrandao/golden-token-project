package br.com.woodriver.gtonboardingapi.application.port.output

import br.com.woodriver.gtonboardingapi.application.domain.Transaction
import br.com.woodriver.gtonboardingapi.application.domain.Wallet

interface PaymentRepositoryPort {

    fun saveOrUpdate(customerId: String, transaction: Transaction)
    fun saveOrUpdate(wallet: Wallet)
    fun findWalletByCustomerId(customerId: String): Wallet
    fun findTransactionByAuthorizationId(customerId: String, authorizationId: String): Transaction

}