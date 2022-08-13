package br.com.woodriver.gtonboardingapi.adapter.out.repository

import br.com.woodriver.gtonboardingapi.adapter.out.repository.converter.toDomain
import br.com.woodriver.gtonboardingapi.adapter.out.repository.converter.toEntity
import br.com.woodriver.gtonboardingapi.adapter.out.repository.converter.toTransactionDomain
import br.com.woodriver.gtonboardingapi.adapter.out.repository.entity.CustomerEntity
import br.com.woodriver.gtonboardingapi.adapter.out.repository.entity.WalletEntity
import br.com.woodriver.gtonboardingapi.adapter.out.repository.entity.WalletEntity.Companion.BALANCE_OPERATION_TYPE
import br.com.woodriver.gtonboardingapi.adapter.out.repository.entity.WalletEntity.Companion.TRANSACTION_OPERATION_TYPE
import br.com.woodriver.gtonboardingapi.application.domain.Customer
import br.com.woodriver.gtonboardingapi.application.domain.Transaction
import br.com.woodriver.gtonboardingapi.application.domain.Wallet
import br.com.woodriver.gtonboardingapi.application.exception.CustomerNotFoundException
import br.com.woodriver.gtonboardingapi.application.port.output.CustomerRepositoryPort
import br.com.woodriver.gtonboardingapi.application.port.output.PaymentRepositoryPort
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper
import org.springframework.stereotype.Repository

@Repository
class PaymentRepository(
    val dynamoDBMapper: DynamoDBMapper
): PaymentRepositoryPort {

    override fun saveOrUpdate(customerId: String, transaction: Transaction) {
        dynamoDBMapper.save(transaction.toEntity(customerId))
    }

    override fun saveOrUpdate(wallet: Wallet) {
        dynamoDBMapper.save(wallet.toEntity())
    }

    override fun findWalletByCustomerId(customerId: String): Wallet {
        return dynamoDBMapper.load(WalletEntity::class.java, customerId, BALANCE_OPERATION_TYPE)?.toDomain()
            ?: throw CustomerNotFoundException("Could not find wallet for Customer with [CustomerId=$customerId]")
    }

    override fun findTransactionByAuthorizationId(customerId: String, authorizationId: String): Transaction {
        return dynamoDBMapper.load(WalletEntity::class.java, customerId, TRANSACTION_OPERATION_TYPE + authorizationId)?.toTransactionDomain()
            ?: throw CustomerNotFoundException("Could not find transaction for Customer with [authorization_id=$authorizationId]")
    }
}