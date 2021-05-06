package jsy.stock.stocktradinghelper.stock.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import jsy.stock.stocktradinghelper.stock.fragment.StockPurchaseFragment
import jsy.stock.stocktradinghelper.stock.navigation.fragment.MyStockNavigationFragment

class MainViewPagerAdapter(fragmentActivity: FragmentActivity) : FragmentStateAdapter(fragmentActivity)  {
    override fun getItemCount(): Int = 2

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 ->  MyStockNavigationFragment()
            1 ->  StockPurchaseFragment()
            else -> StockPurchaseFragment()
        }
    }

}