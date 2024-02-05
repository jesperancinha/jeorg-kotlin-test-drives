package org.jesperancinha.ktd.crums1.crum14

import org.jesperancinha.console.consolerizer.console.ConsolerizerComposer

/**
 * Created by jofisaes on 03/07/2021
 */
class StringsInKotlin {
    companion object {
        @JvmStatic
        fun main(args: Array<String> = emptyArray()) {
            ConsolerizerComposer.outSpace()
                .cyan(ConsolerizerComposer.title("Crum 14 - Strings in Kotlin"))
                .yellow("Lyrics from Zeca - Afonso - Venham mais cinco")
                .yellow("https://www.youtube.com/watch?v=E47asLfITQA")
                .yellow("https://lyricstranslate.com/nl/venham-mais-cinco-bring-me-five-more.html")
                .reset()

            val lyric1 = StringBuffer("Venham mais cinco, duma assentada que eu pago já").toString()
            val lyric2 = StringBuffer("Venham mais cinco, duma assentada que eu pago já").toString()
            ConsolerizerComposer.outSpace()
                .none()
                .blue("This is the original").orange(lyric1).newLine()
                .blue("With substring using until").orange(lyric1.subSequence(5 until 10)).newLine()
                .blue("With substring using until").orange(lyric1.substring(5 until 15)).newLine()
                .blue("We can create a string like this").orange("This is a great lyric: $lyric1").newLine()
                .blue("We can split the lyric").orange(lyric1.split(" ")).newLine()

            val (venham, mais, cinco) = lyric1.split(" ")

            ConsolerizerComposer.outSpace()
                .blue("By destructuring the first three tokens we have:")
                .orange(venham)
                .orange(mais)
                .orange(cinco)
                .reset()

            val translatedSentence = lyric1.replace(Regex("[a-z]")) {
                when (it.value) {
                    "a" -> "0"
                    "b" -> "2"
                    "c" -> "3"
                    else -> "99"
                }
            }

            ConsolerizerComposer.outSpace()
                .blue("We can use the replace function with a very rich way of reading:")
                .orange(translatedSentence)
                .reset()

            ConsolerizerComposer.outSpace()
                .blue("In kotlin, the referential comparator is === whereas in Java it it ==:")
                .orange(lyric1)
                .blue("Comparing with ==")
                .orange(lyric1 == lyric2)
                .blue("Comparing with ===")
                .orange(lyric1 === lyric2)
                .reset()

            ConsolerizerComposer.outSpace()
                .blue("Printing a character just for fun:")
                .orange('\u0910')
                .reset()
            ConsolerizerComposer.outSpace()
                .blue("Printing lyric \"$lyric1\" as a column")
                .reset()
            lyric1.forEach {
                ConsolerizerComposer.out()
                    .orange(it)
                    .reset()
            }
        }
    }
}