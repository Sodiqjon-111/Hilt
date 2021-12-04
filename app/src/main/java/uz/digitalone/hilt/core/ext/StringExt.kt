package uz.digitalone.driverapp.core.ext

import formatted
import uz.digitalone.hilt.application.Constants
import java.text.SimpleDateFormat
import java.util.*

fun String?.isMail(): Boolean {
    if (this.isNullOrBlank()) return false
    val emailRegex = "^[A-Za-z](.*)([@]{1})(.{1,})(\\.)(.{1,})";
    return emailRegex.toRegex().matches(this)
}

fun String?.isValidCode(): Boolean {
    if (this.isNullOrBlank()) return false
    return this.length == Constants.VERIFICATION_CODE_LENGTH
}

fun String?.isValidPassword(): Boolean {
    if (this.isNullOrBlank()) return false
    return this.length >= Constants.MIN_PASSWORD_LENGTH
}

fun String?.convertTimeZoneToDate(
    fromDateFormat: String = "yyyy-MM-dd'T'HH:mm:ss'Z'",
    toDateFormat: String = "yyyy/MM/dd-hh:mm"
): String? {
    if (this.isNullOrBlank()) return null
    val format = SimpleDateFormat(fromDateFormat)
    val date = format.parse(this)
    return date?.formatted(toDateFormat)
}

fun String?.longToTime(toDateFormat: String = "hh:mm"): String? {
    if (this.isNullOrBlank()) return null
    val date = Date(this.toLong())
    return date.formatted(toDateFormat)
}