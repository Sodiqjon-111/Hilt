
/**
 * Created by artCore on 11/16/17.
 */
fun <E>MutableList<E>.doWhenIndexIsSafe(elem: E, modification: MutableList<E>.(index: Int) -> Unit) {
  val index = this.indexOf(elem)
  if (index >= 0) {
    this.modification(index)
  }
}