package br.com.woodriver.gtonboardingapi.application.util

import com.fasterxml.jackson.databind.ObjectMapper


var mapper = ObjectMapper()

fun Any.toJsonString(): String = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(this)
