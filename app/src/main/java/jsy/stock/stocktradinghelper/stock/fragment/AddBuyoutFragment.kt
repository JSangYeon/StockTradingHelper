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
import jsy.stock.stocktradinghelper.viewmodel.StockViewModel


class AddBuyoutFragment : BaseFragment<FragmentAddBuyoutsBinding>(R.layout.fragment_add_buyouts) {

    private val _stockViewModel: StockViewModel by activityViewModels()
    private val _marketTypeViewModel : MarketTypeViewModel by viewModels()
    private lateinit var sharedPreferences: SharedPreferences
    private lateinit var stock: Stock

    override fun FragmentAddBuyoutsBinding.init() {
        addBuyout = this@AddBuyoutFragment


        binding.apply {

            rgPercentStockPriceTrends.setOnCheckedChangeListener(onCheckedChangeListener)
            sharedPreferences = requireActivity().getPreferences(Context.MODE_PRIVATE) ?: return
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



    private fun getSharedData(sharedPreferences: SharedPreferences, name: String, defaultValue : Int)
    {
//        val defaultValue = PercentOption.BearishFireAddBuyoutPercent.defaultValue

//        with(sharedPreferences.edit()) {
//            putInt(name, 9)
//            commit()
//        }

    }



    private fun initPriceTextView(lifecycleOwner: LifecycleOwner) {

        binding.tvStockAveragePrice.text = stock.averagePrice.toString()

        _marketTypeViewModel.marketType.observe(lifecycleOwner, { marketType ->

            Log.d(tag, "marketType : $marketType")

            val firePrice : Int
            val waterPrice : Int

            when (marketType!!) {
                MarketType.Bullish -> {
                    val firePercent = sharedPreferences.getInt(PercentOption.BullishFireAddBuyoutPercent.name, PercentOption.BullishFireAddBuyoutPercent.defaultValue)
                    val waterPercent = sharedPreferences.getInt(PercentOption.BullishWaterAddBuyoutPercent.name, PercentOption.BullishWaterAddBuyoutPercent.defaultValue)

                    firePrice = (stock.averagePrice * (1 + (firePercent.toFloat() / 100))).toInt()
                    waterPrice = (stock.averagePrice * (1 - (waterPercent.toFloat() / 100))).toInt()

                }

                MarketType.Bearlish -> {
                    val firePercent = sharedPreferences.getInt(PercentOption.BearishFireAddBuyoutPercent.name, PercentOption.BearishFireAddBuyoutPercent.defaultValue)
                    val waterPercent = sharedPreferences.getInt(PercentOption.BearishWaterAddBuyoutPercent.name, PercentOption.BearishWaterAddBuyoutPercent.defaultValue)

                    firePrice = (stock.averagePrice * (1 + (firePercent.toFloat() / 100))).toInt()
                    waterPrice = (stock.averagePrice * (1 - (waterPercent.toFloat() / 100))).toInt()

                }
            }

            binding.tvFireAddBuyoutsPrice.text = firePrice.toString()
            binding.tvWaterAddBuyoutsPrice.text = waterPrice.toString()

        })
    }
}