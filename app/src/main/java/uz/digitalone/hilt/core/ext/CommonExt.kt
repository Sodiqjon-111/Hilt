@file:JvmName("CommonExt")


import android.content.Context
import android.content.pm.PackageManager
import android.util.Log
import androidx.fragment.app.Fragment
import uz.digitalone.hilt.BuildConfig
import uz.digitalone.hilt.R


fun hasOreo() = (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O)
fun hasLollipop() = (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP)
fun hasNougat() = (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N)
fun isKitKat() = (android.os.Build.VERSION.SDK_INT == android.os.Build.VERSION_CODES.KITKAT)
fun hasKitKat() = (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.KITKAT)

fun hasLollipop(func: () -> Unit) {
    if (hasLollipop()) {
        func()
    }
}

fun screenslog(mess: String, tag: String = "Screens") {
  if(isLogsEnabled())  Log.d(tag, mess)
}

fun logd(mess: String, tag: String = "myLogs") {
  if(isLogsEnabled())  Log.d(tag, mess)
}

fun isLogsEnabled() = BuildConfig.DEBUG


fun doForPreLollipop(func: () -> Unit) {
  if (hasLollipop().not()) { func() }
}

fun hasKitKat(func: () -> Unit) {
  if (hasKitKat()) {
    func()
  }
}

fun Context.isTablet() = resources.getBoolean(R.bool.isTablet)
fun Fragment.isTablet() = resources.getBoolean(R.bool.isTablet)

fun Context.getAppBuildVersion(): String? {
    val name = try {
        val pInfo = packageManager.getPackageInfo(packageName, 0)
        pInfo.versionName
    } catch (e: PackageManager.NameNotFoundException) {
        e.printStackTrace()
        null
    }

    return name
}
