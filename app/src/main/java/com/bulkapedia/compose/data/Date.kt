package com.bulkapedia.compose.data

import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

val timeFormat: DateTimeFormatter = DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm:ss")
val yearFormat: DateTimeFormatter = DateTimeFormatter.ofPattern("dd.MM.yyyy")

fun now(): LocalDateTime {
    return LocalDateTime.now()
}

fun nowTimeFormat(): String = now().format(timeFormat)

fun nowYearFormat(): String = now().format(yearFormat)

fun String.toYearDate(): LocalDateTime {
    return LocalDateTime.parse(this, yearFormat)
}

fun String.toDateTime(): LocalDateTime {
    return LocalDateTime.parse(this, timeFormat)
}
