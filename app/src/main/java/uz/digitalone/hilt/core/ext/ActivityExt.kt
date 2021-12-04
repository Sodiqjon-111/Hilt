

import android.app.Activity
import android.content.Context
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.asynclayoutinflater.view.AsyncLayoutInflater

/**
 * Created by artCore on 28.12.15.
 */
fun AppCompatActivity.hideKeyboard(view: View){
    val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
    imm.hideSoftInputFromWindow(view.windowToken, 0)
}

fun AppCompatActivity.toggleKeyboard(view: View){
    val inputManager = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager;
    inputManager.toggleSoftInputFromWindow(view.applicationWindowToken, InputMethodManager.SHOW_FORCED, 0);
}

fun Activity.loadAsync(@LayoutRes res: Int, target: ViewGroup? = null, action: View.() -> Unit) =
AsyncLayoutInflater(this).inflate(res, target)
{ view, _, parent ->
    with(parent) {
        this?.addView(view)
        action(view)
    }
}
