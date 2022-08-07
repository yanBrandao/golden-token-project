package br.com.woodriver.gtonboardingapi.application.util

import org.jasypt.util.password.StrongPasswordEncryptor


fun String.encrypt(): String {
    val passwordEncryptor = StrongPasswordEncryptor()
    return passwordEncryptor.encryptPassword(this)
}

const val EMPTY = ""