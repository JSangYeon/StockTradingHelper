package jsy.stock.stocktradinghelper.stock.fragment

import android.content.Context
import android.content.SharedPreferences
import android.util.Log
import android.widget.RadioGroup
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.LifecycleOwner
import androidx.navigation.Navigation
import jsy.stock.stocktradinghelper.R
import jsy.stock.stocktradinghelper.base.BaseFragment
import jsy.stock.stocktradinghelper.databinding.FragmentAddBuyoutsBinding
import jsy.stock.stocktradinghelper.enums.MarketType
import jsy.stock.stocktradinghelper.enums.PercentOption
import jsy.stock.stocktradinghelper.room.Stock
import jsy.stock.stocktradinghelper.viewmodel.MarketTypeViewModel
import jsy.stock.stocktradinghelper.viewmodel.SettingViewModel
import jsy.stock.stocktradinghelper.viewmodel.StockViewModel


class AddBuyoutFragment : BaseFragment<FragmentAddBuyoutsBinding>(R.layout.fragment_add_buyouts) {

    private val _stockViewModel: StockViewModel by activityViewModels()
    private val _marketTypeViewModel : MarketTypeViewModel by viewModels()
    private lateinit var stock: Stock
    private val _settingViewModel : SettingViewModel by activityViewModels()


    override fun FragmentAddBuyoutsBinding.init() {
        addBuyout = this@AddBuyoutFragment

        binding.apply {
            rgPercentStockPriceTrends.setOnCheckedChangeListener(onCheckedChangeListener)
            stock = _stockViewModel.getStock()
            tvTitle.text = stock.stockName
            initPriceTextView(lifecycleOwner!!)
        }

    }

    private val onCheckedChangeListener = RadioGroup.OnCheckedChangeListener { _, id ->

        when(id)
        {
            R.id.rb_percent_bullish_market -> {
                _marketTypeViewModel.setMarketValue(MarketType.Bullish)

                Log.d(tag, "rb_percent_bullish_market_checked")


//                val bullishWaterPrice = calculateWaterPrice(stock.averagePrice, firePercent)

            }

            R.id.rb_percent_bearish_market -> {

                _marketTypeViewModel.setMarketValue(MarketType.Bearlish)
                Log.d(tag, "rb_percent_bearish_market checked")
            }

        }


    }

    private fun calculateFirePrice(price:Int, percent:Int) : Int
    {
        return 0
    }

    private fun calculateWaterPrice() : Int
    {
        return 0
    }

    fun btnSecondToFirst() {
        view?.let { Navigation.findNavController(it).navigate(R.id.action_add_buyouts_to_my_stock_balance) }
    }



//    private fun getSharedData(sharedPreferences: SharedPreferences, name: String, defaultValue : Int)
//    {
////        val defaultValue = PercentOption.BearishFireAddBuyoutPercent.defaultValue
//
////        with(sharedPreferences.edit()) {
////            putInt(name, 9)
////            commit()
////        }
//
//    }



    private fun initPriceTextView(lifecycleOwner: LifecycleOwner) {

        binding.tvStockAveragePrice.text = stock.averagePrice.toString()

        _marketTypeViewModel.marketType.observe(lifecycleOwner, { marketType ->

            Log.d(tag, "marketType : $marketType")

            val firePrice : Int
            val waterPrice : Int

            when (marketType!!) {
                MarketType.Bullish -> {

                    _settingViewModel.bullishFire.observe(lifecycleOwner, {
                        Log.d(tag,"bullishFire : $it")
                        binding.tvFireAddBuyoutsPrice.text = (stock.averagePrice * (1 + (it.toFloat() / 100))).toInt().toString()
                    })

                    _settingViewModel.bullishWater.observe(lifecycleOwner, {
                        Log.d(tag,"bullishWater : $it")
                        binding.tvWaterAddBuyoutsPrice.text = (stock.averagePrice * (1 - (it.toFloat() / 100))).toInt().toString()
                    })

                }

                MarketType.Bearlish -> {

                    _settingViewModel.bearishFire.observe(lifecycleOwner, {
                        Log.d(tag,"bearishFire : $it")
                        binding.tvFireAddBuyoutsPrice.text = (stock.averagePrice * (1 + (it.toFloat() / 100))).toInt().toString()
                    })


                    _settingViewModel.bearishWater.observe(lifecycleOwner, {
                        Log.d(tag,"bearishWater : $it")
                        binding.tvWaterAddBuyoutsPrice.text = (stock.averagePrice * (1 - (it.toFloat() / 100))).toInt().toString()
                    })
                }
            }

        })
    }
}