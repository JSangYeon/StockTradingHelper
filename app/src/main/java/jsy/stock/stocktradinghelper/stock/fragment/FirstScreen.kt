package jsy.stock.stocktradinghelper.stock.fragment

import android.util.Log
import android.widget.Toast
import androidx.navigation.Navigation
import androidx.navigation.Navigation.findNavController
import jsy.stock.stocktradinghelper.R
import jsy.stock.stocktradinghelper.base.BaseFragment
import jsy.stock.stocktradinghelper.databinding.FragmentFirstScreenBinding

class FirstScreen : BaseFragment<FragmentFirstScreenBinding>(R.layout.fragment_first_screen) {

    override fun FragmentFirstScreenBinding.init() {
        firstScreen = this@FirstScreen
    }


    fun btnFirstToSecond()
    {
        view?.let { findNavController(it).navigate(R.id.action_first_screen_to_second_screen) }
    }

    fun btnFirstToThird()
    {

        view?.let { findNavController(it).navigate(R.id.action_first_screen_to_third_screen) }
    }


}