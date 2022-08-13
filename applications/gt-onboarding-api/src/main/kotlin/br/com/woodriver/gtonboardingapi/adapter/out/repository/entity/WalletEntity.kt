package br.com.woodriver.gtonboardingapi.adapter.out.repository.entity

import br.com.woodriver.gtonboardingapi.application.util.EMPTY
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBRangeKey
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable
import java.math.BigDecimal
import java.time.LocalDateTime
import java.util.*

@DynamoDBTable(tableName = "GTWallet")
data class WalletEntity(
    @DynamoDBHashKey(attributeName = "CustomerId")
    var customerId: String = EMPTY,
    @DynamoDBRangeKey(attributeName = "Operation")
    var operation: String = EMPTY,
    @DynamoDBAttribute
    var amount: Double = 0.0,
    @DynamoDBAttribute
    var balance: Double = 0.0,
    @DynamoDBAttribute
    var transactionType: String = EMPTY,
    @DynamoDBAttribute
    var authorizationId: String = EMPTY,
    @DynamoDBAttribute
    var createdAt: Date = Date(),
    @DynamoDBAttribute
    var alreadyRefund: Boolean = false
) {
    companion object {
        const val BALANCE_OPERATION_TYPE = "wallet-current-balance"
        const val TRANSACTION_OPERATION_TYPE = "transaction-"
    }
}