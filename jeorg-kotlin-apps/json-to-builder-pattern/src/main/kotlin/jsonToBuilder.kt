import com.google.gson.GsonBuilder
import java.io.BufferedReader
import java.io.BufferedWriter

private fun readLinesFromBuffer(bufferedReader: BufferedReader, bufferedWriter: BufferedWriter) {
    val readLines: List<String> = bufferedReader.readLines()
    readLines.forEach { line -> bufferedWriter.write(convertToBuilder(line)) }
}

fun convertToBuilder(line: String): String {
    val sb = StringBuilder()
    sb.append("ParentBuilder.create()")
    GsonBuilder().create().fromJson(line, HashMap::class.java)?.forEach { (key, value) ->
        sb.append(".$key".replace("_","").replace("class","clazz"))
        if (isNumeric(value.toString()) || key.toString().contains("Type")) {
            sb.append("($value)")
        } else {
            sb.append("(\"$value\")")
        }
    }
    sb.append(".build();")
    System.out.println(sb.toString())
    return sb.toString()
}

fun isNumeric(value: String): Boolean {
    return value.matches("-?\\d+(\\.\\d+)?".toRegex())
}

