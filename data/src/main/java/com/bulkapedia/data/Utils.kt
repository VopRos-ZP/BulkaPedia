package com.bulkapedia.data

import com.google.firebase.firestore.DocumentSnapshot
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

inline fun <reified T : Any> T.asMap() : Map<String, Any?> {
    val props = T::class.java.declaredFields.associateBy { it.name }
    return props.keys.associateWith { props[it]?.get(this) }
}

inline fun<reified T, DTO> DocumentSnapshot.docToObject(
    dtoClass: Class<DTO>,
    transform: (DTO) -> T
): T? {
    return try { toObject(dtoClass)?.let(transform) } catch (_: Exception) { null }
}

val timeFormat: DateTimeFormatter = DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm:ss")
val yearFormat: DateTimeFormatter = DateTimeFormatter.ofPattern("dd.MM.yyyy")

fun now(): LocalDateTime {
    return LocalDateTime.now()
}

fun nowTimeFormat(): String = now().format(timeFormat)

fun nowYearFormat(): String = now().format(yearFormat)

fun String.toYearDate(): LocalDate {
    return LocalDate.parse(this, yearFormat)
}

fun String.toDateTime(): LocalDateTime {
    return LocalDateTime.parse(this, timeFormat)
}