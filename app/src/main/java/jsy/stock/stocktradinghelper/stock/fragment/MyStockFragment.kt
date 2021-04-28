package jsy.stock.stocktradinghelper.stock.fragment

import android.util.Log
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.schedulers.Schedulers
import jsy.stock.stocktradinghelper.R
import jsy.stock.stocktradinghelper.base.BaseFragment
import jsy.stock.stocktradinghelper.databinding.FragmentMyStockBinding
import jsy.stock.stocktradinghelper.room.StockDB
import jsy.stock.stocktradinghelper.stock.adapter.StockAccountBalanceAdapter
import jsy.stock.stocktradinghelper.viewmodel.StockViewModel

class MyStockFragment : BaseFragment<FragmentMyStockBinding>(R.layout.fragment_my_stock) {

    private val stockViewModel: StockViewModel by activityViewModels()
    private val disposable = CompositeDisposable()

    override fun FragmentMyStockBinding.init() {
        myStock = this@MyStockFragment

        val stockAdapter = StockAccountBalanceAdapter(stockViewModel)

        val stockDB = StockDB.getInstance(requireContext())!!

        rvStockAccountBalance.apply {
            layoutManager = LinearLayoutManager(context).apply {
                orientation = LinearLayoutManager.VERTICAL
            }
            addItemDecoration(
                    DividerItemDecoration(
                            requireContext(),
                            (layoutManager as LinearLayoutManager).orientation
                    )
            )
            adapter = stockAdapter
        }

        disposable.add(
                stockDB.stockDao().getAll()
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe({
                            stockViewModel.setStockList(it)
                            rvStockAccountBalance.adapter?.notifyDataSetChanged()
                        },
                                { error -> Log.e("stock", "error : ${error.printStackTrace()}") })
        )


    }

    fun btnClick() {
        TODO("네비게이션 구현 필요")
    }
}