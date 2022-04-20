package kg.ruslan.core.halpers

import android.content.Context
import java.io.IOException
import java.io.InputStream
import kotlin.math.log

class Utils(private val context: Context) {
    fun getJsonFromAssets(fileName: String): String? = try {
        val inputStream: InputStream = context.assets.open(fileName)
        val size: Int = inputStream.available()
        val buffer = ByteArray(size)
        inputStream.read(buffer)
        inputStream.close()
        String(buffer)
    } catch (e: IOException) {
        e.printStackTrace()
        null
    }
}
