package br.com.innovarix.runningclub.core.extensions

import android.util.Patterns
import java.time.LocalDate
import java.time.Period
import java.time.format.DateTimeFormatter
import java.time.format.DateTimeParseException


private const val ELEVEN = 11
private const val EIGHT = 8

fun String.unMask() = this.replace("[^0-9]".toRegex(), "")

fun String.isValidEmail(): Boolean =
    this.isNotEmpty() && Patterns.EMAIL_ADDRESS.matcher(this).matches()


fun String?.isValidCpf(): Boolean {
    if (this.isNullOrBlank() || this.unMask().length > 11) return false

    val cpfDigits = this.mapNotNull { it.digitToIntOrNull() }

    if (cpfDigits.size != 11 || cpfDigits.all { it == cpfDigits[0] }) {
        return false
    }
    val dv1 = cpfDigits.calculateCpfDigit(weight = 10)
    val dv2 = cpfDigits.calculateCpfDigit(weight = 11)

    return when {
        dv1 != cpfDigits[9] ->  false
        dv2 != cpfDigits[10] -> false
        else -> true
    }
}

private fun List<Int>.calculateCpfDigit(weight: Int): Int {
    val sum = this.subList(0, weight - 1)
        .foldIndexed(0) { index, acc, digit ->
            acc + digit * (weight - index)
        }

    val remainder = sum % 11
    return if (remainder < 2) 0 else 11 - remainder
}

fun String.isValidBirthdate(today: LocalDate = LocalDate.now()): Boolean {
    val minAge = 18
    val maxAge = 90
    val formatter = DateTimeFormatter.ofPattern("ddMMyyyy");

    val birthdate: LocalDate
    try {
        birthdate = LocalDate.parse(this.unMask(), formatter)
    } catch (e: DateTimeParseException) {
        println("Invalid date format: $this")
        return false
    }

    val age = Period.between(birthdate, today)

   return when {
        birthdate.isAfter(today) -> false
        age.years < minAge -> false
        age.years > maxAge -> false
        else -> true
    }
}

fun String?.isValidPhoneNumber(): Boolean {
    if (this.isNullOrBlank()) {
        return false
    }

    val digitsOnly = this.filter { it.isDigit() }

    val length = digitsOnly.length
    if (length !in 10..11) {
        return false
    }

    val ddd = digitsOnly.substring(0, 2).toIntOrNull() ?: return false
    if (ddd < 11) {
        return false
    }

    val number = digitsOnly.substring(2)
    return when (length) {
        11 -> number.length == 9 && number.startsWith('9')
        10 -> number.length == 8 && number.first() in '2'..'5'
        else -> false
    }
}