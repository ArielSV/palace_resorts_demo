package com.example.palace_resorts.utils.email

object EmailUtils {

    fun validateEmail(email: String): Boolean {
        val regex = """^[a-zA-Z0-9._%+-]{3,}@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,}$""".toRegex()
        return regex.matches(email)
    }

    fun validatePassword(password: String): Boolean {
        val regex = """^(?=.*[A-Za-z]{5,})(?=.*\d.*\d)[A-Za-z\d]{7,}$""".toRegex()
        return regex.matches(password)
    }
}