package br.com.woodriver.gtonboardingapi.utils

import br.com.woodriver.gtonboardingapi.application.domain.Customer
import org.jeasy.random.EasyRandom

inline fun <reified T> randomObject(): T {
    return EasyRandom().nextObject(T::class.java)
}

fun randomCustomer(documentNumber: String): Customer {
    return randomObject<Customer>().copy(
        documentNumber = documentNumber
    )
}