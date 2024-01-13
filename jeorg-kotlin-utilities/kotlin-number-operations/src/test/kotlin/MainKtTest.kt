import org.junit.jupiter.api.Test

class MainKtTest {


    @Test
    fun `should test bytes`() {
        var i:Byte = 2
        var x:Byte = 2
        while (i <= 100) {
            println ( "i is currently $i" ) ;
//            i = i + x
//            i = i + 1.toByte()
//            i += 1.toByte()
            i = (i + x).toByte()
            i = (i + 1.toByte()).toByte()
        }
    }
}