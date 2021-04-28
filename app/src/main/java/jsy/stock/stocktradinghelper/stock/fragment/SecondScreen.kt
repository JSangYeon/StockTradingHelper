package jsy.stock.stocktradinghelper.stock.fragment

import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import androidx.navigation.Navigation.findNavController
import jsy.stock.stocktradinghelper.R
import jsy.stock.stocktradinghelper.base.BaseFragment
import jsy.stock.stocktradinghelper.databinding.FragmentSecondScreenBinding


class SecondScreen: BaseFragment<FragmentSecondScreenBinding>(R.layout.fragment_second_screen) {

    override fun FragmentSecondScreenBinding.init() {
        secondScreen = this@SecondScreen
    }


    fun btnSecondToFirst()
    {
        view?.let { findNavController(it).navigate(R.id.action_second_screen_to_first_screen) }
    }

    fun btnSecondToThird()
    {

        view?.let { findNavController(it).navigate(R.id.action_second_screen_to_third_screen) }
    }
}