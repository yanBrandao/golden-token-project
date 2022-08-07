package br.com.woodriver.gtonboardingapi.adapter.input.web.controller

import com.fasterxml.jackson.databind.PropertyNamingStrategy
import com.fasterxml.jackson.databind.annotation.JsonNaming

@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy::class)
data class ErrorMessage(
    val status: String,
    val date: String,
    val exceptionMessage: String
)