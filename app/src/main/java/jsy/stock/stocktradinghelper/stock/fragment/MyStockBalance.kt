package jsy.stock.stocktradinghelper.stock.fragment

import android.util.Log
import androidx.activity.viewModels
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.schedulers.Schedulers
import jsy.stock.stocktradinghelper.R
import jsy.stock.stocktradinghelper.base.BaseFragment
import jsy.stock.stocktradinghelper.databinding.FragmentFirstScreenBinding
import jsy.stock.stocktradinghelper.function.whenNotNull
import jsy.stock.stocktradinghelper.room.StockDB
import jsy.stock.stocktradinghelper.stock.adapter.StockAccountBalanceAdapter
import jsy.stock.stocktradinghelper.viewmodel.StockViewModel

class MyStockBalance : BaseFragment<FragmentFirstScreenBinding>(R.layout.fragment_first_screen) {

    private val _stockViewModel: StockViewModel by activityViewModels()
    private val disposable = CompositeDisposable()


    override fun FragmentFirstScreenBinding.init() {

        firstScreen = this@MyStockBalance

        val stockAdapter = StockAccountBalanceAdapter(_stockViewModel)

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
                            _stockViewModel.setStockList(it)
                            rvStockAccountBalance.adapter?.notifyDataSetChanged()
                        },
                                { error -> Log.e("stock", "error : ${error.printStackTrace()}") })
        )

   }


    fun btnFirstToSecond()
    {
        view?.let { Navigation.findNavController(it).navigate(R.id.action_my_stock_balance_to_second_screen) }
    }

    fun btnFirstToThird()
    {
        view?.let { Navigation.findNavController(it).navigate(R.id.action_my_stock_balance_to_third_screen) }
    }


}