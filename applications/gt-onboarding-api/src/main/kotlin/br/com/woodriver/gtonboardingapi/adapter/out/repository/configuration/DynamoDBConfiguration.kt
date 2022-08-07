package br.com.woodriver.gtonboardingapi.adapter.out.repository.configuration

import com.amazonaws.auth.AWSCredentials
import com.amazonaws.auth.BasicAWSCredentials
import com.amazonaws.client.builder.AwsClientBuilder
import com.amazonaws.client.builder.AwsClientBuilder.EndpointConfiguration
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClient
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper
import org.springframework.beans.factory.annotation.Value
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Bean


@Configuration
class DynamoDBConfiguration {
    @Value("\${amazon.dynamodb.endpoint}")
    private lateinit var  amazonDynamoDBEndpoint: String

    @Value("\${amazon.dynamodb.region}")
    private lateinit var  amazonDynamoDBRegion: String

    @Value("\${amazon.aws.accesskey}")
    private lateinit var  amazonAWSAccessKey: String

    @Value("\${amazon.aws.secretkey}")
    private lateinit var amazonAWSSecretKey: String

    @Bean
    @ConditionalOnProperty(value = ["profile"], havingValue = "production", matchIfMissing = false)
    fun amazonDynamoDB(): AmazonDynamoDB = AmazonDynamoDBClient.builder()
            .withEndpointConfiguration(EndpointConfiguration(amazonDynamoDBEndpoint, amazonDynamoDBRegion))
            .build()

    @Bean
    @ConditionalOnProperty(value = ["profile"], matchIfMissing = true)
    fun amazonDynamoDBLocal(): AmazonDynamoDB = AmazonDynamoDBClient.builder()
        .withEndpointConfiguration(EndpointConfiguration("http://localhost:4566", "sa-east-1"))
        .build()

    @Bean
    fun amazonAWSCredentials(): AWSCredentials {
        return BasicAWSCredentials(
            amazonAWSAccessKey, amazonAWSSecretKey
        )
    }

    @Bean
    fun dynamoDBMapper(amazonDynamoDB: AmazonDynamoDB): DynamoDBMapper {
        return DynamoDBMapper(amazonDynamoDB)
    }
}