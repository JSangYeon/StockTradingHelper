package jsy.stock.stocktradinghelper.stock.fragment

import android.util.Log
import android.widget.CompoundButton
import android.widget.RadioGroup
import androidx.fragment.app.activityViewModels
import androidx.navigation.Navigation
import jsy.stock.stocktradinghelper.R
import jsy.stock.stocktradinghelper.base.BaseFragment
import jsy.stock.stocktradinghelper.databinding.FragmentAddBuyoutsBinding
import jsy.stock.stocktradinghelper.viewmodel.StockViewModel


class AddBuyoutFragment : BaseFragment<FragmentAddBuyoutsBinding>(R.layout.fragment_add_buyouts) {

    private val _stockViewModel: StockViewModel by activityViewModels()
    private var bullishWaterAddBuyoutPercent = 5
    private var bearishWaterAddBuyoutPercent = 8
    private var bullishFireAddBuyoutPercent = 5
    private var bearishFIreAddBuyoutPercent = 8


    override fun FragmentAddBuyoutsBinding.init() {
        addBuyout = this@AddBuyoutFragment


        binding.apply {

            rgTypeStockPriceTrends.setOnCheckedChangeListener(onCheckedChangeListener)
            rgPercentStockPriceTrends.setOnCheckedChangeListener(onCheckedChangeListener)

        }



    }

    private val onCheckedChangeListener = RadioGroup.OnCheckedChangeListener { _, id ->

        when(id)
        {
            R.id.rb_price -> {
                Log.d(tag,"rb_price checked")
            }

            R.id.rb_percent -> {
                Log.d(tag,"rb_percent checked")
            }

            R.id.rb_percent_bullish_market -> {
                Log.d(tag,"rb_percent_bullish_market_checked")
            }

            R.id.rb_percent_bearish_market -> {
                Log.d(tag,"rb_percent_bearish_market checked")
            }
        }

    }


    fun btnSecondToFirst() {
        view?.let { Navigation.findNavController(it).navigate(R.id.action_add_buyouts_to_my_stock_balance) }
    }

}