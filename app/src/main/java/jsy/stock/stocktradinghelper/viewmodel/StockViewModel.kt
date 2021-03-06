package jsy.stock.stocktradinghelper.viewmodel

import android.app.AlertDialog
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.schedulers.Schedulers
import jsy.stock.stocktradinghelper.R
import jsy.stock.stocktradinghelper.databinding.CustomDialogStockAddBinding
import jsy.stock.stocktradinghelper.databinding.CustomDialogStockRemoveBinding
import jsy.stock.stocktradinghelper.room.Stock
import jsy.stock.stocktradinghelper.room.StockDB


class StockViewModel : ViewModel() {

    private val _stockList = MutableLiveData<ArrayList<Stock>>()
    private lateinit var stockDB: StockDB
    private var position = 0

    val stockList: LiveData<ArrayList<Stock>>
        get() = _stockList


    fun setStockList(stockList: ArrayList<Stock>) {
        _stockList.value = stockList
    }

    fun setPosition(position: Int) {
        this.position = position
    }

    fun getStock(): Stock = _stockList.value!![position]

    private val disposable = CompositeDisposable()

    fun setStockDB(db: StockDB) {
        stockDB = db
    }

    private fun stockInsert(stock: Stock) {
        disposable.add(
            stockDB.stockDao().insert(stock)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    Log.d("stockDB", "success insert")
                },
                    { error -> Log.e("stock deleteAll", "error : ${error.printStackTrace()}") })
        )
    }

    private fun stockUpdateName(id: Long, stockName: String) {
        disposable.add(
            stockDB.stockDao().updateStockName(id, stockName)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ Log.d("stock", "success") },
                    { error -> Log.e("stock", "error : ${error.printStackTrace()}") })
        )
    }

    private fun stockUpdatePrice(id: Long, price: Int) {
        disposable.add(
            stockDB.stockDao().updateStockPrice(id, price)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ Log.d("stock", "success") },
                    { error -> Log.e("stock", "error : ${error.printStackTrace()}") })
        )
    }

    private fun stockUpdateAccount(id: Long, account: Int) {
        disposable.add(
            stockDB.stockDao().updateStockAccount(id, account)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ Log.d("stock", "success") },
                    { error -> Log.e("stock", "error : ${error.printStackTrace()}") })
        )
    }


    private fun stockDeleteStock(stock: Stock) {
        disposable.add(
            stockDB.stockDao().delete(stock)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ Log.d("stock", "success") },
                    { error -> Log.e("stock", "error : ${error.printStackTrace()}") })
        )
    }


    fun stockDeleteAll() {
        disposable.add(
            stockDB.stockDao().deleteAll()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ Log.d("stock", "success") },
                    { error -> Log.e("stock", "error : ${error.printStackTrace()}") })
        )
    }


    fun btnAddStock(view: View) {

        val dialogBinding = CustomDialogStockAddBinding.inflate(LayoutInflater.from(view.context))

        val builder = AlertDialog.Builder(view.context)

        dialogBinding.apply {
            tvDialogTitle.text = root.resources.getString(R.string.add_stock)
            builder.setView(dialogBinding.root)
                .setPositiveButton("??????") { dialogInterface, i ->
                    if (etStockName.text.toString().isNotEmpty() || etStockAccount.text.toString()
                            .isNotEmpty() || etStockAveragePrice.text.toString().isNotEmpty()
                    ) {
                        val stock = Stock()
                        stock.stockName = etStockName.text.toString()
                        stock.account = etStockAccount.text.toString().toInt()
                        stock.averagePrice = etStockAveragePrice.text.toString().toInt()

                        stockInsert(stock)
                    }

                }

                .setNegativeButton("??????") { dialogInterface, i ->

                }.show()
        }
    }

    fun reviseDialog(view: View, stock: Stock) {
        val dialogBinding = CustomDialogStockAddBinding.inflate(LayoutInflater.from(view.context))

        val builder = AlertDialog.Builder(view.context)

        dialogBinding.apply {

            etStockName.setText(stock.stockName)
            etStockAveragePrice.setText(stock.averagePrice.toString())
            etStockAccount.setText(stock.account.toString())

            builder.setView(dialogBinding.root)
                .setPositiveButton("??????") { dialogInterface, i ->

                    if (etStockName.text.toString().isNotEmpty()) stockUpdateName(
                        stock.id!!,
                        etStockName.text.toString()
                    )

                    if (etStockAccount.text.toString().isNotEmpty()) stockUpdateAccount(
                        stock.id!!,
                        etStockAccount.text.toString().toInt()
                    )

                    if (etStockAveragePrice.text.toString()
                            .isNotEmpty()
                    ) stockUpdatePrice(stock.id!!, etStockAveragePrice.text.toString().toInt())
                }

                .setNegativeButton("??????") { dialogInterface, i ->

                }.show()
        }
    }


    fun removeDialog(view: View, stock: Stock) {
        val dialogBinding =
            CustomDialogStockRemoveBinding.inflate(LayoutInflater.from(view.context))

        val builder = AlertDialog.Builder(view.context)

        dialogBinding.apply {
            builder.setView(dialogBinding.root)
                .setPositiveButton("??????") { dialogInterface, i ->
                    stockDeleteStock(stock)
                }

                .setNegativeButton("??????") { dialogInterface, i ->

                }.show()
        }

    }

}