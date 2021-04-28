package jsy.stock.stocktradinghelper.stock.fragment

import androidx.navigation.Navigation
import androidx.navigation.Navigation.findNavController
import jsy.stock.stocktradinghelper.R
import jsy.stock.stocktradinghelper.base.BaseFragment
import jsy.stock.stocktradinghelper.databinding.FragmentThirdScreenBinding

class ThirdScreen : BaseFragment<FragmentThirdScreenBinding>(R.layout.fragment_third_screen) {

    override fun FragmentThirdScreenBinding.init() {
        thirdScreen = this@ThirdScreen
    }

    fun btnThirdToFirst()
    {
        view?.let { findNavController(it).navigate(R.id.action_third_screen_to_first_screen) }
    }

    fun btnThirdToSecond()
    {

        view?.let { findNavController(it).navigate(R.id.action_third_screen_to_second_screen) }
    }

}