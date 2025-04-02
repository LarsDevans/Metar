package nl.avans.larsbeijaard.metar.data.service

import android.content.ContentValues
import android.content.Context
import android.net.Uri
import android.os.Environment
import android.provider.MediaStore
import kotlinx.coroutines.*
import okhttp3.OkHttpClient
import okhttp3.Request
import java.io.IOException
import java.text.SimpleDateFormat
import java.util.*

class DownloadService(
    private val context: Context,
    private val client: OkHttpClient = OkHttpClient(),
    private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO
) {
    sealed interface DownloadResult {
        object Success : DownloadResult
        data class Failure(val error: String) : DownloadResult
    }

    suspend fun downloadFile(url: String, fileName: String): DownloadResult = withContext(ioDispatcher) {
        try {
            ensureActive()

            val uniqueFileName = createUniqueFileName(fileName)
            val uri = createMediaStoreUri(uniqueFileName)

            if (uri == null) {
                return@withContext DownloadResult.Failure("Failed to create file URI")
            }

            context.contentResolver.openOutputStream(uri)?.use { outputStream ->
                ensureActive()

                client.newCall(Request.Builder().url(url).build())
                    .execute()
                    .also { response ->
                        if (!response.isSuccessful) {
                            throw IOException("HTTP ${response.code}")
                        }
                    }
                    .body?.byteStream()?.use { inputStream ->
                        ensureActive()

                        inputStream.copyTo(outputStream)
                    }
            }

            DownloadResult.Success
        } catch (e: CancellationException) {
            throw e
        } catch (e: IOException) {
            DownloadResult.Failure(e.message ?: "Unknown IO error")
        }
    }

    private fun createUniqueFileName(fileName: String): String {
        val timestamp = SimpleDateFormat("yyyyMMddHHmmss", Locale.getDefault()).format(Date())
        return "${fileName}_$timestamp"
    }

    private fun createMediaStoreUri(fileName: String): Uri? {
        val values = ContentValues().apply {
            put(MediaStore.Images.Media.DISPLAY_NAME, fileName)
            put(MediaStore.Images.Media.MIME_TYPE, "image/png")
            put(MediaStore.Images.Media.RELATIVE_PATH, Environment.DIRECTORY_PICTURES + "/Metar")
        }

        return context.contentResolver.insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, values)
    }
}