package br.com.woodriver.gtonboardingapi.adapter.input.web.controller

import br.com.woodriver.gtonboardingapi.application.exception.CustomerAlreadyExistsException
import br.com.woodriver.gtonboardingapi.application.exception.CustomerNotFoundException
import com.amazonaws.services.dynamodbv2.model.ResourceNotFoundException
import org.springframework.http.HttpStatus
import org.springframework.http.HttpStatus.BAD_REQUEST
import org.springframework.http.HttpStatus.NOT_FOUND
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestControllerAdvice
import org.springframework.web.context.request.WebRequest
import java.util.*


@RestControllerAdvice
class AdviceController {

    @ExceptionHandler(value = [CustomerNotFoundException::class])
    @ResponseStatus(value = NOT_FOUND)
    fun resourceNotFoundException(ex: CustomerNotFoundException, request: WebRequest?): ErrorMessage? {
        return ErrorMessage(
            CUSTOMER_ERROR_NOTFOUND,
            Date().toString(),
            ex.message
        )
    }

    @ExceptionHandler(value = [CustomerAlreadyExistsException::class])
    @ResponseStatus(value = BAD_REQUEST)
    fun resourceAlreadyExistException(ex: CustomerAlreadyExistsException, request: WebRequest?): ErrorMessage? {
        return ErrorMessage(
            CUSTOMER_ERROR_ALREADY_EXISTS,
            Date().toString(),
            ex.message
        )
    }


    companion object{
        const val CUSTOMER_ERROR_NOTFOUND = "CUS001"
        const val CUSTOMER_ERROR_ALREADY_EXISTS = "CUS002"
    }

}