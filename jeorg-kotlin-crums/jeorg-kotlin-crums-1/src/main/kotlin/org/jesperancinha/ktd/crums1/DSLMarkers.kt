package org.jesperancinha.ktd.crums1

@DslMarker
annotation class HtmlDsl

@HtmlDsl
class Html {
    private val elements = mutableListOf<String>()

    fun head(init: Head.() -> Unit) {
        val head = Head().apply(init)
        elements.add("<head>${head.render()}</head>")
    }

    fun body(init: Body.() -> Unit) {
        val body = Body().apply(init)
        elements.add("<body>${body.render()}</body>")
    }

    fun render() = elements.joinToString("\n")
}

@HtmlDsl
class Head {
    private val elements = mutableListOf<String>()

    fun title(value: String) {
        elements.add("<title>$value</title>")
    }

    fun render() = elements.joinToString("")
}

@HtmlDsl
class Body {
    private val elements = mutableListOf<String>()

    fun h1(value: String) {
        elements.add("<h1>$value</h1>")
    }

    fun p(value: String) {
        elements.add("<p>$value</p>")
    }

    fun render() = elements.joinToString("")
}


class DSLMarkers {
    companion object {
        fun html(init: Html.() -> Unit): String {
            val html = Html().apply(init)
            return html.render()
        }

        @JvmStatic
        fun main(args: Array<String> = emptyArray()) {
            val result = html {
                head {
                    title("My Page")
                }
                body {
                    h1("Welcome")
                    p("This is a type-safe DSL with @DslMarker.")
                }
            }
            println(result)
           val result2= html {
                head {
//                  body {
//                      h1("My Page")
//                  }
                }
                body {
                    h1("Welcome")
                }
            }
            println(result2)
        }
    }
}