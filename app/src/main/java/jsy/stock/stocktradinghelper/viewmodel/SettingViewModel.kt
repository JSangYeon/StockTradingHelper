package jsy.stock.stocktradinghelper.viewmodel

import android.content.SharedPreferences
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import jsy.stock.stocktradinghelper.enums.PercentOption

class SettingViewModel : ViewModel() {

    private lateinit var sharedPreferences: SharedPreferences
    private val _bullishFire = MutableLiveData<Int>()
    val bullishFire: LiveData<Int>
        get() = _bullishFire


    private val _bullishWater = MutableLiveData<Int>()
    val bullishWater: LiveData<Int>
        get() = _bullishWater


    private val _bearishFire = MutableLiveData<Int>()
    val bearishFire: LiveData<Int>
        get() = _bearishFire


    private val _bearishWater = MutableLiveData<Int>()
    val bearishWater: LiveData<Int>
        get() = _bearishWater


    fun setBullishFireValue(){

    }


    fun init(sharedPreferences: SharedPreferences)
    {

        this.sharedPreferences = sharedPreferences
        _bullishFire.value = sharedPreferences.getInt(PercentOption.BullishFireAddBuyoutPercent.name, PercentOption.BullishFireAddBuyoutPercent.defaultValue)
        _bullishWater.value = sharedPreferences.getInt(PercentOption.BullishWaterAddBuyoutPercent.name, PercentOption.BullishWaterAddBuyoutPercent.defaultValue)
        _bearishFire.value = sharedPreferences.getInt(PercentOption.BearishFireAddBuyoutPercent.name, PercentOption.BearishFireAddBuyoutPercent.defaultValue)
        _bearishWater.value = sharedPreferences.getInt(PercentOption.BearishWaterAddBuyoutPercent.name, PercentOption.BearishWaterAddBuyoutPercent.defaultValue)


    }


}