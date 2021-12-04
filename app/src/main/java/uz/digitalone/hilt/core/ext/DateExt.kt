import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date
import java.util.Locale
import java.util.TimeZone

/**
 * Created by Zohidjon on 27.09.2021.
 */

fun Date.formatted(pattern: String) : String {
  val format = SimpleDateFormat(pattern, Locale.getDefault())
  return format.format(this)
}

fun Date.toCalendar(): Calendar {
  val calendar = Calendar.getInstance()
  calendar.time = this
  return calendar
}

fun Calendar.year(): Int {
  return this.get(Calendar.YEAR)
}

fun Calendar.month(): Int {
  return this.get(Calendar.MONTH)
}

fun Calendar.date(): Int {
  return this.get(Calendar.DATE)
}

fun Calendar.hourOfDay(): Int {
  return this.get(Calendar.HOUR_OF_DAY)
}

fun Calendar.minute(): Int {
  return this.get(Calendar.MINUTE)
}

fun Calendar.toDate(useTimeZone: TimeZone = TimeZone.getDefault()): Date {
  return time.apply { timeZone = useTimeZone }
}