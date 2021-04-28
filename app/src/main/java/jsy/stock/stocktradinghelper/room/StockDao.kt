package jsy.stock.stocktradinghelper.room


import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy.REPLACE
import androidx.room.Query
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Observable

/* StockDao.kt */

@Dao
interface StockDao {
    @Query("SELECT * FROM cat")
    fun getAll(): Observable<List<Stock>>

    /* import android.arch.persistence.room.OnConflictStrategy.REPLACE */
    @Insert(onConflict = REPLACE)
    fun insert(stock: Stock) : Completable

    @Query("DELETE from cat")
    fun deleteAll() : Completable
}