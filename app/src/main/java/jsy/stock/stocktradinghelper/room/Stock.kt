package jsy.stock.stocktradinghelper.room

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

/* Stock.kt */

@Entity(tableName = "stock")
class Stock(@PrimaryKey var id: Long?,
            @ColumnInfo(name = "stockName") var stockName: String, // 주식 명
            @ColumnInfo(name = "averagePrice") var averagePrice: Int, // 평균단가
            @ColumnInfo(name = "account") var account: Int // 개수
){
    constructor(): this(null,"", 0,0)
}
