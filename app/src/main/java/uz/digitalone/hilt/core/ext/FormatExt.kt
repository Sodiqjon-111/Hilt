
import java.math.BigDecimal
import java.text.DecimalFormat

/**
 * Created by artCore on 12/27/17.
 */
const val DEFAULT_DECIMAL_FORMAT = "0.##"

fun Float.formatDecimals(format: String = DEFAULT_DECIMAL_FORMAT): String {
  val df = DecimalFormat(format)
  return df.format(this)
}

fun Double.formatDecimals(format: String = DEFAULT_DECIMAL_FORMAT): String {
  val df = DecimalFormat(format)
  return df.format(this)
}

fun BigDecimal.formatDecimals(scaleDigit: Int = 2, roundingMode: Int = BigDecimal.ROUND_DOWN) : String {
  val bd = setScale(scaleDigit, roundingMode)
  val df = DecimalFormat()
  df.maximumFractionDigits = 2
  df.minimumFractionDigits = 0
  df.isGroupingUsed = false
  return df.format(bd)
}