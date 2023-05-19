package com.vopros.data

import java.time.LocalDate
import java.time.format.DateTimeFormatter

val timeFormat: DateTimeFormatter = DateTimeFormatter.ofPattern("dd.MM.yyyy hh:mm:ss")
val yearFormat: DateTimeFormatter = DateTimeFormatter.ofPattern("dd.MM.yyyy")

fun now(): LocalDate {
    return LocalDate.now()
}

fun nowTimeFormat(): String = now().toTimeFormat()

fun nowYearFormat(): String = now().toYearFormat()

fun String.toYearDate(): LocalDate {
    return LocalDate.parse(this, yearFormat)
}

fun String.toDateTime(): LocalDate {
    return LocalDate.parse(this, timeFormat)
}

fun LocalDate.toTimeFormat(): String = this.format(timeFormat)

fun LocalDate.toYearFormat(): String = this.format(yearFormat)
