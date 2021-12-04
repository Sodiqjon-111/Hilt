import android.graphics.PorterDuff
import android.widget.EditText
import uz.digitalone.driverapp.core.ext.getColorCompat

/**
 * Created by artCore on 10/26/17.
 */
fun EditText.setBottomLineColor(color: Int) {
  background.mutate().setColorFilter(this.context.getColorCompat(color), PorterDuff.Mode.SRC_ATOP)
}