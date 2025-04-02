package nl.avans.larsbeijaard.metar.data.service

import android.content.ContentValues
import android.content.Context
import android.os.Environment
import android.provider.MediaStore
import android.util.Log
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import okhttp3.OkHttpClient
import okhttp3.Request
import java.io.IOException
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class DownloadService(
    private val context: Context
) {
    suspend fun downloadFile(url: String, fileName: String) {
        withContext(Dispatchers.IO) {
            val timestamp = SimpleDateFormat("yyyyMMddHHmmss", Locale.getDefault()).format(Date())
            val uniqueFileName = "${fileName}_$timestamp"

            val values = ContentValues().apply {
                put(MediaStore.Images.Media.DISPLAY_NAME, uniqueFileName)
                put(MediaStore.Images.Media.MIME_TYPE, "image/png")
                put(MediaStore.Images.Media.RELATIVE_PATH, Environment.DIRECTORY_PICTURES + "/Metar")
            }

            val uri = context.contentResolver.insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, values)

            if (uri == null) {
                Log.e("DownloadFile", "Failed to create file URI")
                return@withContext
            }

            try {
                context.contentResolver.openOutputStream(uri)?.use { outputStream ->
                    val response = OkHttpClient().newCall(
                        Request.Builder().url(url).build()
                    ).execute()

                    response.body?.byteStream()?.use { inputStream ->
                        inputStream.copyTo(outputStream)
                    } ?: Log.e("DownloadFile", "Failed to download file: Empty response body")
                }
            } catch (e: IOException) {
                Log.e("DownloadFile", "Error downloading file", e)
            }
        }
    }
}