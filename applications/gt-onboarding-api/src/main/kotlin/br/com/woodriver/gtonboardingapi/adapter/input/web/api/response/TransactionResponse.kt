package br.com.woodriver.gtonboardingapi.adapter.input.web.api.response

import br.com.woodriver.gtonboardingapi.application.util.EMPTY
import com.fasterxml.jackson.databind.PropertyNamingStrategy
import com.fasterxml.jackson.databind.annotation.JsonNaming
import java.math.BigDecimal
    
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy::class)
data class TransactionResponse(
    var customerId: String,
    var balance: BigDecimal = BigDecimal.ZERO,
    var transaction: ArrayList<ItemTransactionResponse>
) {
    data class ItemTransactionResponse(
        var amount: BigDecimal = BigDecimal.ZERO,
        var authorizationId: String  = EMPTY
    )
}