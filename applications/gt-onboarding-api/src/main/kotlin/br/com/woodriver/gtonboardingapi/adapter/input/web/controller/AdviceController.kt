package br.com.woodriver.gtonboardingapi.adapter.input.web.controller

import br.com.woodriver.gtonboardingapi.adapter.input.web.api.response.ErrorMessageResponse
import br.com.woodriver.gtonboardingapi.application.exception.*
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
    fun resourceNotFoundException(ex: CustomerNotFoundException, request: WebRequest?): ErrorMessageResponse? {
        return ErrorMessageResponse(
            CUSTOMER_ERROR_NOTFOUND,
            Date().toString(),
            ex.message
        )
    }

    @ExceptionHandler(value = [CustomerAlreadyExistsException::class])
    @ResponseStatus(value = BAD_REQUEST)
    fun resourceAlreadyExistException(ex: CustomerAlreadyExistsException, request: WebRequest?): ErrorMessageResponse? {
        return ErrorMessageResponse(
            CUSTOMER_ERROR_ALREADY_EXISTS,
            Date().toString(),
            ex.message
        )
    }

    @ExceptionHandler(value = [WithoutBalanceForOperationException::class])
    @ResponseStatus(value = BAD_REQUEST)
    fun walletWithoutBalanceException(ex: WithoutBalanceForOperationException, request: WebRequest?): ErrorMessageResponse? {
        return ErrorMessageResponse(
            TRANSACTION_ERROR_WITHOUT_BALANCE,
            Date().toString(),
            ex.message
        )
    }

    @ExceptionHandler(value = [InvalidTransactionTypeException::class])
    @ResponseStatus(value = BAD_REQUEST)
    fun invalidTransactionTypeException(ex: InvalidTransactionTypeException, request: WebRequest?): ErrorMessageResponse? {
        return ErrorMessageResponse(
            TRANSACTION_ERROR_OPERATION_TYPE,
            Date().toString(),
            ex.message
        )
    }

    @ExceptionHandler(value = [AlreadyRefundTransactionException::class])
    @ResponseStatus(value = BAD_REQUEST)
    fun alreadyRefundTransaction(ex: AlreadyRefundTransactionException, request: WebRequest?): ErrorMessageResponse? {
        return ErrorMessageResponse(
            TRANSACTION_ERROR_ALREADY_REFUND,
            Date().toString(),
            ex.message
        )
    }


    companion object{
        const val CUSTOMER_ERROR_NOTFOUND = "CUS001"
        const val CUSTOMER_ERROR_ALREADY_EXISTS = "CUS002"
        const val TRANSACTION_ERROR_WITHOUT_BALANCE = "TRA001"
        const val TRANSACTION_ERROR_OPERATION_TYPE = "TRA002"
        const val TRANSACTION_ERROR_ALREADY_REFUND = "TRA003"
    }

}