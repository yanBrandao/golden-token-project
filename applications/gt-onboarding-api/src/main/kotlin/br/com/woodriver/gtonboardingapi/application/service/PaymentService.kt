package br.com.woodriver.gtonboardingapi.application.service

import br.com.woodriver.gtonboardingapi.application.domain.Transaction
import br.com.woodriver.gtonboardingapi.application.domain.Wallet
import br.com.woodriver.gtonboardingapi.application.port.input.CreditUseCase
import br.com.woodriver.gtonboardingapi.application.port.input.DebitUseCase
import br.com.woodriver.gtonboardingapi.application.port.input.RefundUseCase
import br.com.woodriver.gtonboardingapi.application.port.output.PaymentRepositoryPort
import org.springframework.stereotype.Service

@Service
class PaymentService(
    val paymentRepositoryPort: PaymentRepositoryPort
): CreditUseCase, DebitUseCase, RefundUseCase {
    override fun executeCredit(customerId: String, transaction: Transaction): Wallet {
        val wallet = paymentRepositoryPort.findWalletByCustomerId(customerId)

        wallet.credit(transaction, paymentRepositoryPort)

        return wallet
    }

    override fun executeDebit(customerId: String, transaction: Transaction): Wallet {
        val wallet = paymentRepositoryPort.findWalletByCustomerId(customerId)

        wallet.debit(transaction, paymentRepositoryPort)
        return wallet
    }

    override fun executeRefund(customerId: String, transaction: Transaction): Wallet {
        val wallet = paymentRepositoryPort.findWalletByCustomerId(customerId)

        wallet.refund(transaction, paymentRepositoryPort)
        return wallet
    }
}