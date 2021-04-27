package jsy.stock.stocktradinghelper.stock.fragment

import androidx.navigation.Navigation
import jsy.stock.stocktradinghelper.R
import jsy.stock.stocktradinghelper.base.BaseFragment
import jsy.stock.stocktradinghelper.databinding.FragmentFirstScreenBinding

class FirstScreen : BaseFragment<FragmentFirstScreenBinding>(R.layout.fragment_first_screen) {

    override fun init() {

        binding.firstScreen = this@FirstScreen
    }


    fun btnFirstToSecond()
    {
        view?.let { Navigation.findNavController(it).navigate(R.id.action_first_screen_to_second_screen) }
    }

    fun btnFirstToThird()
    {

        view?.let { Navigation.findNavController(it).navigate(R.id.action_first_screen_to_third_screen) }
    }
}