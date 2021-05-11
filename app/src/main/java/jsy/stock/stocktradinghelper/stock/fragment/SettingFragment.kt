package jsy.stock.stocktradinghelper.stock.fragment

import android.content.Context
import android.content.SharedPreferences
import android.util.Log
import android.view.View
import androidx.fragment.app.activityViewModels
import jsy.stock.stocktradinghelper.R
import jsy.stock.stocktradinghelper.base.BaseFragment
import jsy.stock.stocktradinghelper.databinding.FragmentSettingBinding
import jsy.stock.stocktradinghelper.enums.PercentOption
import jsy.stock.stocktradinghelper.generated.callback.OnClickListener
import jsy.stock.stocktradinghelper.viewmodel.SettingViewModel

class SettingFragment : BaseFragment<FragmentSettingBinding>(R.layout.fragment_setting) {

    private lateinit var sharedPreferences:SharedPreferences

    private val _settingViewModel : SettingViewModel by activityViewModels()



    override fun FragmentSettingBinding.init() {


        binding.apply {
            settingFragment = this@SettingFragment
            sharedPreferences = requireActivity().getPreferences(Context.MODE_PRIVATE) ?: return
            settingViewModel = _settingViewModel

            initText()

        }

    }

    private fun initText(){
//        binding.etSettingBullishFire.setText(sharedPreferences.getInt(PercentOption.BullishFireAddBuyoutPercent.name, PercentOption.BullishFireAddBuyoutPercent.defaultValue).toString())
//        binding.etSettingBullishWater.setText(sharedPreferences.getInt(PercentOption.BullishWaterAddBuyoutPercent.name, PercentOption.BullishWaterAddBuyoutPercent.defaultValue).toString())
//        binding.etSettingBearishFire.setText(sharedPreferences.getInt(PercentOption.BearishFireAddBuyoutPercent.name, PercentOption.BearishFireAddBuyoutPercent.defaultValue).toString())
//        binding.etSettingBearishWater.setText(sharedPreferences.getInt(PercentOption.BearishWaterAddBuyoutPercent.name, PercentOption.BearishWaterAddBuyoutPercent.defaultValue).toString())
    }

    fun btnSettingBullishFire(){
        if(binding.etSettingBearishFire.text.toString().isEmpty()) return

        val value = binding.etSettingBullishFire.text.toString().toInt()

        if(value>=0)
        {
            _settingViewModel.setBullishFire(value)
        }
    }


    fun btnSettingBullishWater(){
        if(binding.etSettingBullishWater.text.toString().isEmpty()) return

        val value = binding.etSettingBullishWater.text.toString().toInt()

        if(value>=0)
        {

            _settingViewModel.setBullishWater(value)
        }

    }


    fun btnSettingBearishFire(){
        if(binding.etSettingBearishFire.text.toString().isEmpty()) return

        val value = binding.etSettingBearishFire.text.toString().toInt()


        if(value>=0) _settingViewModel.setBearishFire(value)

    }

    fun btnSettingBearishWater(){
        if(binding.etSettingBearishWater.text.toString().isEmpty()) return

        val value = binding.etSettingBearishWater.text.toString().toInt()

        if(value>=0) _settingViewModel.setBearishWater(value)
    }




}