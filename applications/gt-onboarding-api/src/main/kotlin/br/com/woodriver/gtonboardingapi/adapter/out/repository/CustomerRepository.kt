package br.com.woodriver.gtonboardingapi.adapter.out.repository

import br.com.woodriver.gtonboardingapi.adapter.out.repository.converter.toDomain
import br.com.woodriver.gtonboardingapi.adapter.out.repository.converter.toEntity
import br.com.woodriver.gtonboardingapi.adapter.out.repository.entity.CustomerEntity
import br.com.woodriver.gtonboardingapi.application.domain.Customer
import br.com.woodriver.gtonboardingapi.application.exception.CustomerNotFoundException
import br.com.woodriver.gtonboardingapi.application.port.output.CustomerRepositoryPort
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper
import org.springframework.stereotype.Repository

@Repository
class CustomerRepository(
    val dynamoDBMapper: DynamoDBMapper
): CustomerRepositoryPort {
    override fun saveOrUpdate(customer: Customer): Customer {
        dynamoDBMapper.save(customer.toEntity())
        return customer
    }

    override fun findCustomerById(customerId: String): Customer {
        return dynamoDBMapper.load(CustomerEntity::class.java, customerId)?.toDomain()
            ?: throw CustomerNotFoundException("Could not find Customer with [CustomerId=$customerId]")
    }
}