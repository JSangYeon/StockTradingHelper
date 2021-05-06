package jsy.stock.stocktradinghelper.function

import android.content.res.Resources

val Int.dp: Int
    get() = (this * Resources.getSystem().displayMetrics.density + 0.5f).toInt()

inline fun <T, R> T?.whenNotNull(body: T.() -> R): R? {
    if (this != null) {
        return body()
    }
    return null
}
