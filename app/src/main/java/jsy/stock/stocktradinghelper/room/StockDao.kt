package jsy.stock.stocktradinghelper.room


import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy.REPLACE
import androidx.room.Query
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single

/* StockDao.kt */

@Dao
interface StockDao {
    @Query("SELECT * FROM stock")
    fun getAll(): LiveData<List<Stock>>

    /* import android.arch.persistence.room.OnConflictStrategy.REPLACE */
    @Insert(onConflict = REPLACE)
    fun insert(stock: Stock): Completable

    @Query("Update stock set stockName = :stockName where id = :id")
    fun updateStockName(id: Long, stockName: String): Completable

    @Query("Update stock set averagePrice = :averagePrice where id = :id")
    fun updateStockPrice(id: Long, averagePrice: Int): Completable

    @Query("Update stock set account = :account where id = :id")
    fun updateStockAccount(id: Long, account: Int): Completable

    @Delete
    fun delete(stock: Stock): Completable

    @Query("DELETE from stock")
    fun deleteAll(): Single<Int>
}