package jsy.stock.stocktradinghelper.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import jsy.stock.stocktradinghelper.room.Stock


class StockViewModel : ViewModel() {

    private val _stockList =  MutableLiveData<List<Stock>>()

    val stockList : LiveData<List<Stock>>
        get() = _stockList

    val text = MutableLiveData<CharSequence>().apply{
        value = "Hello World2"
    }

    fun getText():String
    {
        return text.value.toString()
    }

    fun setStockList(stockList: List<Stock>)
    {
        _stockList.value = stockList
    }



}