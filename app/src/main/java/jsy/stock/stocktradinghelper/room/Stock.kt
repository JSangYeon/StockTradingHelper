package jsy.stock.stocktradinghelper.room

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

/* Stock.kt */

@Entity(tableName = "cat")
class Stock(@PrimaryKey var id: Long?,
            @ColumnInfo(name = "catname") var stockName: String?,
            @ColumnInfo(name = "lifespan") var lifeSpan: Int,
            @ColumnInfo(name = "origin") var origin: String
){
    constructor(): this(null,"", 0,"")
}
