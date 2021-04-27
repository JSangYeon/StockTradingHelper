package jsy.stock.stocktradinghelper.stock.fragment

import jsy.stock.stocktradinghelper.R
import jsy.stock.stocktradinghelper.base.BaseFragment
import jsy.stock.stocktradinghelper.databinding.FragmentMyStockBinding

class MyStockFragment : BaseFragment<FragmentMyStockBinding>(R.layout.fragment_my_stock) {
    override fun init() {
        binding.myStock = this@MyStockFragment
        binding.tvHello.text = "12345"
    }

    fun btnClick()
    {
        TODO("네비게이션 구현 필요")
    }
}