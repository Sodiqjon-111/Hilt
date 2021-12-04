import android.app.DownloadManager
import android.app.DownloadManager.Query
import android.app.DownloadManager.Request
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Environment
import android.webkit.MimeTypeMap
import java.io.File

/**
 * Created by alexk on 12.02.18.
 */
fun isFileExists(fileName: String): Boolean = File(getDownloadsFilePath(fileName)).exists()

fun getDownloadsFilePath(fileName: String): String = "${Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS)}/$fileName"

fun getOpenIntent(fileUri: Uri): Intent {
  val intent = Intent()
  intent.action = Intent.ACTION_VIEW
  val fileExtension = getFileExtension(fileUri.path?:"")
  val fileType = MimeTypeMap.getSingleton().getMimeTypeFromExtension(fileExtension)
  intent.setDataAndType(fileUri, fileType ?: "*/*")
  intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION)
  return intent
}

private fun getFileExtension(path: String) =
    if (path.contains('.')) path.substring(path.lastIndexOf('.') + 1, path.length) else null

fun isReadyToOpen(context: Context, fileName: String): Boolean {
  val downloadManager = context.getSystemService(Context.DOWNLOAD_SERVICE) as DownloadManager
  val cursor = downloadManager.query(Query())
  if (cursor != null && cursor.moveToFirst()) {
    for (i in 0 until cursor.count) {
      cursor.moveToPosition(i)
      val title = cursor.getString(cursor.getColumnIndex(DownloadManager.COLUMN_TITLE))
      val status = cursor.getInt(cursor.getColumnIndex(DownloadManager.COLUMN_STATUS))
      if (title == fileName) {
        return status == DownloadManager.STATUS_SUCCESSFUL
      }
    }
  }
  return true
}

fun isEnqueuedToDownloads(context: Context, fileName: String): Boolean {
  val downloadManager = context.getSystemService(Context.DOWNLOAD_SERVICE) as DownloadManager
  val cursor = downloadManager.query(Query())
  if (cursor != null && cursor.moveToFirst()) {
    for (i in 0 until cursor.count) {
      cursor.moveToPosition(i)
      val title = cursor.getString(cursor.getColumnIndex(DownloadManager.COLUMN_TITLE))
      val status = cursor.getInt(cursor.getColumnIndex(DownloadManager.COLUMN_STATUS))
      if (title == fileName) {
        return status == DownloadManager.STATUS_RUNNING
            || status == DownloadManager.STATUS_PENDING
            || status == DownloadManager.STATUS_PAUSED
      }
    }
  }
  return false
}

fun isFileExistsAndCanBeOpened(context: Context, fileName: String): Boolean {
  val isExists = isFileExists(fileName)
  val canBeOpened = isReadyToOpen(context, fileName)
  if (isExists && canBeOpened.not()) {
    val file = File(getDownloadsFilePath(fileName))
    if (file.exists()) file.delete()
  }
  return isExists && canBeOpened
}

fun enqueueFileDownload(context: Context, fileUrl: String, fileName: String): Long {
  val request = DownloadManager.Request(Uri.parse(fileUrl))
  request.setDestinationInExternalPublicDir(Environment.DIRECTORY_DOWNLOADS, fileName)
  request.allowScanningByMediaScanner()
  request.setNotificationVisibility(Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED)
  request.setVisibleInDownloadsUi(true)

  val downloadManager = context.getSystemService(Context.DOWNLOAD_SERVICE) as DownloadManager
  return downloadManager.enqueue(request)
}

fun getDownloadCompletedBroadcastReceiver(downloadId: Long, downloadedAction: () -> Unit): BroadcastReceiver {
  return object : BroadcastReceiver() {
    override fun onReceive(context: Context, intent: Intent) {
      val newDownloadId = intent.getLongExtra(DownloadManager.EXTRA_DOWNLOAD_ID, 0)
      if (intent.action == DownloadManager.ACTION_DOWNLOAD_COMPLETE && newDownloadId == downloadId) {
        checkAndOpenDownloadedFile(context)
      }
    }

    private fun checkAndOpenDownloadedFile(context: Context) {
      val downloadManager = context.getSystemService(Context.DOWNLOAD_SERVICE) as DownloadManager
      val c = downloadManager.query(Query().setFilterById(downloadId))
      if (c.moveToFirst()) {
        val statusIndex = c.getColumnIndex(DownloadManager.COLUMN_STATUS)
        if (DownloadManager.STATUS_SUCCESSFUL == c.getInt(statusIndex)) {
          downloadedAction.invoke()
        }
      }
    }
  }
}