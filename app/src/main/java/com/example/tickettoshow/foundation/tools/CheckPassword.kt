package com.example.tickettoshow.foundation.tools

fun checkPassword(password: String): Boolean {
    if (password.length < 8) {
        return false
    }
    var digitInPassword = false
    var capitalLetterInPassword = false
    var lowercaseLetterInPassword = false
    var specialCharacterInPassword = false

    for (letter in password) {
        if (letter.isDigit()) {
            digitInPassword = true
        }
        if (letter.isUpperCase()) {
            capitalLetterInPassword = true
        }
        if (letter.isLowerCase()) {
            lowercaseLetterInPassword = true
        }
        if (letter in setOf('!', '?', '.', ',', '#', '$', '%')) {
            specialCharacterInPassword = true
        }
    }

    return (digitInPassword and capitalLetterInPassword and lowercaseLetterInPassword and specialCharacterInPassword)
}
