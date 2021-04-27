package jsy.stock.stocktradinghelper.room


import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

/* StockDB.kt */

@Database(entities = [Stock::class], version = 1)
abstract class StockDB: RoomDatabase() {
    abstract fun stockDao(): StockDao

    companion object {
        private var INSTANCE: StockDB? = null

        fun getInstance(context: Context): StockDB? {
            if (INSTANCE == null) {
                synchronized(StockDB::class) {
                    INSTANCE = Room.databaseBuilder(context.applicationContext,
                        StockDB::class.java, "stock.db")
                        .fallbackToDestructiveMigration()
                        .build()
                }
            }
            return INSTANCE
        }

        fun destroyInstance() {
            INSTANCE = null
        }
    }
}