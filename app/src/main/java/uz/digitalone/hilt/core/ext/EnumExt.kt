import java.util.*

/**
 * Created by artCore on 9/15/17.
 */
inline fun <reified T : Enum<T>> deserialize(string: String): T? = enumValues<T>().find { it.name.lowercase(
    Locale.getDefault()
) == string.lowercase(Locale.getDefault())
}
inline fun <reified T : Enum<T>> find(predicate: (T) -> Boolean): T? = enumValues<T>().find { predicate(it) }
fun <T: Enum<T>> Enum<T>.serialize(): String = this.name.lowercase(Locale.getDefault())
operator fun <T: Enum<T>> Enum<T>.component1(): String = this.name.lowercase(Locale.getDefault())
