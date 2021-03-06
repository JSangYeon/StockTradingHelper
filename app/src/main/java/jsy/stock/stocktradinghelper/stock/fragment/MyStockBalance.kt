package jsy.stock.stocktradinghelper.stock.fragment

import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.MobileAds
import io.reactivex.rxjava3.disposables.CompositeDisposable
import jsy.stock.stocktradinghelper.R
import jsy.stock.stocktradinghelper.base.BaseFragment
import jsy.stock.stocktradinghelper.databinding.FragmentMyStockBalanceBinding
import jsy.stock.stocktradinghelper.room.StockDB
import jsy.stock.stocktradinghelper.stock.adapter.StockAccountBalanceAdapter
import jsy.stock.stocktradinghelper.viewmodel.StockViewModel

class MyStockBalance : BaseFragment<FragmentMyStockBalanceBinding>(R.layout.fragment_my_stock_balance) {

    private val _stockViewModel: StockViewModel by activityViewModels()
    private val disposable = CompositeDisposable()

    override fun FragmentMyStockBalanceBinding.init() {
        initAdMob()

        firstScreen = this@MyStockBalance
        stockViewModel = _stockViewModel

        val stockAdapter = StockAccountBalanceAdapter(_stockViewModel, disposable)

        val stockDB = StockDB.getInstance(requireContext())!!

        _stockViewModel.setStockDB(stockDB)

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

        lifecycleOwner?.let {
            stockDB.stockDao().getAll().observe(it) { stockList ->
                _stockViewModel.setStockList(ArrayList(stockList))
                rvStockAccountBalance.adapter?.notifyDataSetChanged()
            }
        }

    }

    fun initAdMob(){
        MobileAds.initialize(requireContext())
        val adRequest = AdRequest.Builder().build()
        binding.adView.loadAd(adRequest)
    }



}