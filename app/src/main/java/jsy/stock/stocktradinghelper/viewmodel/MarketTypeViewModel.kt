package jsy.stock.stocktradinghelper.viewmodel

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import jsy.stock.stocktradinghelper.enums.MarketType

class MarketTypeViewModel  : ViewModel() {

    private val _marketType = MutableLiveData<MarketType>().apply {
        value = MarketType.Bullish // default 상승장
    }

    val marketType: LiveData<MarketType>
        get() = _marketType


    fun setMarketValue(marketType: MarketType) {
        _marketType.value = marketType
    }


}