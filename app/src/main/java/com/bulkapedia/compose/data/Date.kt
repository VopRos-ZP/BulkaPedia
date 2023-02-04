package com.bulkapedia.compose.data

import java.time.LocalDate
import java.time.format.DateTimeFormatter

val timeFormat: DateTimeFormatter = DateTimeFormatter.ofPattern("dd.MM.yyyy hh:mm:ss")
val yearFormat: DateTimeFormatter = DateTimeFormatter.ofPattern("dd.MM.yyyy")

fun now(): LocalDate {
    return LocalDate.now()
}

fun nowTimeFormat(): String = now().format(timeFormat)

fun nowYearFormat(): String = now().format(yearFormat)

fun String.toYearDate(): LocalDate {
    return LocalDate.parse(this, yearFormat)
}

fun String.toDateTime(): LocalDate {
    return LocalDate.parse(this, timeFormat)
}
