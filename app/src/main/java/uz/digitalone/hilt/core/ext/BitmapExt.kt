import android.graphics.Bitmap
import android.graphics.BitmapFactory
import java.io.ByteArrayOutputStream

fun Bitmap.compressedByteArr(percent: Int): ByteArray {
    val bitmapData: ByteArray
    val bos = ByteArrayOutputStream()
    compress(Bitmap.CompressFormat.JPEG, percent, bos)
    bitmapData = bos.toByteArray()
    return bitmapData
}

fun Bitmap.compressed(percent: Int): Bitmap {
    val bitmapData: ByteArray
    val bos = ByteArrayOutputStream()
    compress(Bitmap.CompressFormat.JPEG, percent, bos)
    bitmapData = bos.toByteArray()
    return BitmapFactory.decodeByteArray(bitmapData, 0, bitmapData.size)

}

fun Bitmap.toByteArray() = compressedByteArr(100)