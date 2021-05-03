package jsy.stock.stocktradinghelper.function

inline fun <T, R> T?.whenNotNull(body: T.() -> R): R? {
    if (this != null) {
        return body()
    }
    return null
}
