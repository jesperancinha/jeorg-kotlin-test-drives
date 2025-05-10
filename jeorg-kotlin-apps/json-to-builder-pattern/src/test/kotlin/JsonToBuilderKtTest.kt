import io.kotest.core.spec.style.WordSpec
import org.assertj.core.api.Assertions.assertThat

class JsonToBuilderKtTest : WordSpec() {

    init {
        /**
         * Designed for a possible use in mongodb, hence the use of _id and _class for tests
         */
        "convertToBuilder" should {

            val test: String =
                convertToBuilder("""{"_id":"28","_class":"com.jesperancinha.Building","floors":2,"name":"Climaxxx"}""")

            assertThat(test).isEqualTo("""ParentBuilder.create().floors(2.0).name("Climaxxx").id(28).clazz("com.jesperancinha.Building").build();""")
        }

        "convertToBuilder1" should {
            val convertToBuilder: String = convertToBuilder("")
            assertThat(convertToBuilder).isEqualTo("""ParentBuilder.create().build();""")
        }

        "isNumeric" should {
            assertThat(isNumeric("1233")).isTrue()
            assertThat(isNumeric("1233.34")).isTrue()
            assertThat(isNumeric("sdfdsf")).isFalse()
            assertThat(isNumeric("12fdf")).isFalse()
        }
    }
}