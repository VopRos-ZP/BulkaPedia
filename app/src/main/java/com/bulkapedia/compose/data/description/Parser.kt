package com.bulkapedia.compose.data.description

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.withStyle
import com.bulkapedia.compose.ui.theme.Blue
import com.bulkapedia.compose.ui.theme.Teal200

class Parser {

    private val START_TIME_CODE = "[start_time]"
    private val END_TIME_CODE = "[end_time]"

    fun parseToAnnotated(text: String): AnnotatedString {
        val tokens = tokenize(text)
        return buildAnnotatedString {
            tokens.forEach { t ->
                withStyle(t.style) {
                    when (t) {
                        is Token.TTimeCode -> append(t.time)
                        is Token.TText -> append(t.text)
                    }
                }
            }
        }
    }

    private fun tokenize(text: String): List<Token> {
        val tokens = mutableListOf<Token>()
        val splited = text.split(END_TIME_CODE)
        splited.forEach { str ->
            if (str.startsWith(START_TIME_CODE)) {
                val time = str.substringAfter(START_TIME_CODE, str)
                tokens.add(Token.TTimeCode(time))
            } else {
                tokens.add(Token.TText(str))
            }
        }
        return tokens
    }

    sealed class Token(val style: SpanStyle) {
        data class TTimeCode(val time: String): Token(SpanStyle(color = Blue))
        data class TText(val text: String): Token(SpanStyle(color = Color.White))
    }

}