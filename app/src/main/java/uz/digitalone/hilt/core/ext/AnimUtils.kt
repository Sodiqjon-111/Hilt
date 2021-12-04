

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.animation.ArgbEvaluator
import android.animation.ObjectAnimator
import android.content.Context
import android.transition.Transition
import androidx.annotation.ColorRes
import androidx.core.content.ContextCompat


fun animateColor(context: Context, target: Any?, propertyName: String,
                 @ColorRes colorFrom: Int, @ColorRes colorTo: Int, duration: Int = 400, callback: () -> Unit) {
  val anim = ObjectAnimator.ofObject(target, propertyName,
      ArgbEvaluator(), ContextCompat.getColor(context, colorFrom),
      ContextCompat.getColor(context, colorTo))
  anim.duration = duration.toLong()
  anim.addListener(animOnFinish { callback.invoke() })
  anim.start()
}

fun animOnFinish(callback: () -> Unit): AnimatorListenerAdapter {
  return object : AnimatorListenerAdapter() {
    override fun onAnimationEnd(animation: Animator?) {
      super.onAnimationEnd(animation)
      callback.invoke()
    }
  }
}

@Suppress("unused")
fun animOnStart(callback: () -> Unit): AnimatorListenerAdapter {
  return object : AnimatorListenerAdapter() {
    override fun onAnimationStart(animation: Animator?) {
      super.onAnimationStart(animation)
      callback.invoke()
    }
  }
}

fun transitionListener(transitionStart: () -> Unit, transitionEnd: () -> Unit): Transition.TransitionListener {
  return object : Transition.TransitionListener {
    override fun onTransitionResume(transition: Transition?) {

    }

    override fun onTransitionPause(transition: Transition?) {

    }

    override fun onTransitionCancel(transition: Transition?) {

    }

    override fun onTransitionStart(transition: Transition?) {
      transitionStart.invoke()
    }

    override fun onTransitionEnd(transition: Transition?) {
      transitionEnd.invoke()
    }
  }
}
