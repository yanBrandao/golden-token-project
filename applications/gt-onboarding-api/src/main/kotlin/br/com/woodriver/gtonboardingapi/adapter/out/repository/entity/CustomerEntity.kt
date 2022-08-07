package br.com.woodriver.gtonboardingapi.adapter.out.repository.entity

import br.com.woodriver.gtonboardingapi.application.util.EMPTY
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBDocument
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable
import java.util.*

@DynamoDBTable(tableName = "GTCustomers")
data class CustomerEntity(
    @DynamoDBHashKey(attributeName = "CustomerId")
    var customerId: String = EMPTY,
    @DynamoDBAttribute
    var name: String = EMPTY,
    @DynamoDBAttribute
    var lastName: String = EMPTY,
    @DynamoDBAttribute
    var nickname: String = EMPTY,
    @DynamoDBAttribute
    var documentNumber: String = EMPTY,
    @DynamoDBAttribute
    var addressLine: String = EMPTY,
    @DynamoDBAttribute
    var phoneEntity: PhoneEntity = PhoneEntity(),
    @DynamoDBAttribute
    var email: String = EMPTY,
    @DynamoDBAttribute
    var birthDate: Date = Date(),
    @DynamoDBAttribute
    var password: String = EMPTY
) {
    @DynamoDBDocument
    data class PhoneEntity(
        var ddi: String = EMPTY,
        var ddd: String = EMPTY,
        var number: String = EMPTY
    )
}