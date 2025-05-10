import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
internal class JsonToBuilderKtOldTest {

    @BeforeEach
    fun setUp() {
    }

    @AfterEach
    fun tearDown() {
    }

    /**
     * Designed for a possible use in mongodb, hence the use of _id and _class for tests
     */
    @Test
    fun convertToBuilder() {

        val test:String  = convertToBuilder("""{"_id":"28","_class":"com.jesperancinha.Building","floors":2,"name":"Climaxxx"}""")

        assertThat(test).isEqualTo("""ParentBuilder.create().floors(2.0).name("Climaxxx").id(28).clazz("com.jesperancinha.Building").build();""")
    }

    @Test
    fun convertToBuilder1() {
        val convertToBuilder: String = convertToBuilder("")
        assertThat(convertToBuilder).isEqualTo("""ParentBuilder.create().build();""")
    }

    @Test
    fun isNumeric() {
        assertThat(isNumeric("1233")).isTrue()
        assertThat(isNumeric("1233.34")).isTrue()
        assertThat(isNumeric("sdfdsf")).isFalse()
        assertThat(isNumeric("12fdf")).isFalse()
    }
}