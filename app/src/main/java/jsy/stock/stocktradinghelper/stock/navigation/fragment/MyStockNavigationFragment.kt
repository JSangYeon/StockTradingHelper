package jsy.stock.stocktradinghelper.stock.navigation.fragment

import android.content.Context
import androidx.activity.OnBackPressedCallback
import androidx.navigation.Navigation
import jsy.stock.stocktradinghelper.R
import jsy.stock.stocktradinghelper.base.BaseFragment
import jsy.stock.stocktradinghelper.databinding.FragmentMyStockBinding
import jsy.stock.stocktradinghelper.stock.navigation.listener.OnBackPressedListener


class MyStockNavigationFragment : BaseFragment<FragmentMyStockBinding>(R.layout.fragment_my_stock) {


    private lateinit var callback: OnBackPressedCallback

    override fun FragmentMyStockBinding.init() {
        myStock = this@MyStockNavigationFragment


    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        callback = object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                val controller = Navigation.findNavController(requireActivity(), R.id.my_nav_host_fragment)
                if (requireActivity() is OnBackPressedListener) (requireActivity() as OnBackPressedListener).onBackPressed()
                else if (!controller.popBackStack())
                    requireActivity().finish()
            }
        }
        requireActivity().onBackPressedDispatcher.addCallback(this, callback)
    }

    override fun onDetach() {
        super.onDetach()
        callback.remove()
    }

    fun btnClick() {
        TODO("네비게이션 구현 필요")
    }
}