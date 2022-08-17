package br.com.woodriver.gtonboardingapi.adapter.out.repository

import br.com.woodriver.gtonboardingapi.adapter.out.repository.entity.WalletEntity
import br.com.woodriver.gtonboardingapi.adapter.out.repository.entity.WalletEntity.Companion.BALANCE_OPERATION_TYPE
import br.com.woodriver.gtonboardingapi.adapter.out.repository.entity.WalletEntity.Companion.TRANSACTION_OPERATION_TYPE
import br.com.woodriver.gtonboardingapi.application.domain.Transaction
import br.com.woodriver.gtonboardingapi.application.domain.Wallet
import br.com.woodriver.gtonboardingapi.application.exception.CustomerNotFoundException
import br.com.woodriver.gtonboardingapi.application.exception.InvalidTransactionTypeException
import br.com.woodriver.gtonboardingapi.utils.randomObject
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper
import io.mockk.every
import io.mockk.justRun
import io.mockk.mockk
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import java.util.*

internal class PaymentRepositoryTest {

    private val dynamoDBMapper: DynamoDBMapper = mockk()
    private val paymentRepository = PaymentRepository(dynamoDBMapper)

    @Test
    fun `Should save a WalletEntity when receive wallet`() {
        val wallet = randomObject<Wallet>()

        justRun { dynamoDBMapper.save(any<WalletEntity>()) }

        paymentRepository.saveOrUpdate(wallet)
    }

    @Test
    fun `Should save a WalletEntity when receive transaction`() {
        val transaction = randomObject<Transaction>()

        justRun { dynamoDBMapper.save(any<WalletEntity>()) }

        paymentRepository.saveOrUpdate(randomObject(), transaction)
    }

    @Test
    fun `Should find wallet by customer_id`() {
        val walletEntity = randomObject<WalletEntity>()
        every { dynamoDBMapper.load(WalletEntity::class.java, any(), BALANCE_OPERATION_TYPE) } returns walletEntity

        paymentRepository.findWalletByCustomerId(UUID.randomUUID().toString())
    }

    @Test
    fun `Should throw exception CustomerNotFound when find wallet`() {
        val walletEntity = randomObject<WalletEntity>()
        every { dynamoDBMapper.load(WalletEntity::class.java, any(), BALANCE_OPERATION_TYPE) } returns null

       assertThrows<CustomerNotFoundException> { paymentRepository.findWalletByCustomerId(UUID.randomUUID().toString()) }
    }

    @Test
    fun `Should throw exception CustomerNotFound when find transaction`() {
        val walletEntity = randomObject<WalletEntity>()
        val customerId = UUID.randomUUID().toString()
        val authorizationId = UUID.randomUUID().toString()

        every { dynamoDBMapper.load(WalletEntity::class.java, any(), TRANSACTION_OPERATION_TYPE + authorizationId) } returns null

        assertThrows<CustomerNotFoundException> { paymentRepository.findTransactionByAuthorizationId(customerId, authorizationId) }
    }

    @Test
    fun `Should not load transaction for a invalid type`() {
        val walletEntity = randomObject<WalletEntity>()
        val customerId = UUID.randomUUID().toString()
        val authorizationId = UUID.randomUUID().toString()

        every { dynamoDBMapper.load(WalletEntity::class.java, any(), TRANSACTION_OPERATION_TYPE + authorizationId) } returns walletEntity

        assertThrows<InvalidTransactionTypeException> { paymentRepository.findTransactionByAuthorizationId(customerId, authorizationId) }
    }

    @Test
    fun `Should load transaction`() {
        val walletEntity = randomObject<WalletEntity>().copy(
            transactionType = "CREDIT"
        )
        val customerId = UUID.randomUUID().toString()
        val authorizationId = UUID.randomUUID().toString()

        every { dynamoDBMapper.load(WalletEntity::class.java, any(), TRANSACTION_OPERATION_TYPE + authorizationId) } returns walletEntity

        paymentRepository.findTransactionByAuthorizationId(customerId, authorizationId)
    }
}