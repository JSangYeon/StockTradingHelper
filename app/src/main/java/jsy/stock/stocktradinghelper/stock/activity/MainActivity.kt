package jsy.stock.stocktradinghelper.stock.activity

import android.os.Bundle
import android.util.Log
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.viewModels
import com.google.android.material.tabs.TabLayoutMediator
import jsy.stock.stocktradinghelper.R
import jsy.stock.stocktradinghelper.base.BaseActivity
import jsy.stock.stocktradinghelper.databinding.ActivityMainBinding
import jsy.stock.stocktradinghelper.stock.adapter.MainViewPagerAdapter
import jsy.stock.stocktradinghelper.stock.navigation.fragment.MyStockNavigationFragment
import jsy.stock.stocktradinghelper.viewmodel.StockViewModel


class MainActivity :  BaseActivity<ActivityMainBinding>(R.layout.activity_main) {

    private val _stockViewModel: StockViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding.apply {
            lifecycleOwner = this@MainActivity
            main = this@MainActivity
            stockViewModel = _stockViewModel
            initView()

        }
    }


    fun stockBtnOnClick()
    {
        Log.d("btnClick", "test")
        // 화면 전환 프래그먼트 선언 및 초기 화면 설정
//        supportFragmentManager.beginTransaction().add(R.id.fl_stock, MyStockFragment()).commit()
    }

    private fun initView() {

        initTab()
    }


    private fun initTab()
    {
        val pager = binding.pager
        val tlStock = binding.tlStock
        val tabs : Array<String> = resources.getStringArray(R.array.list_tab)

        pager.run {
            adapter = MainViewPagerAdapter(this@MainActivity)
            isUserInputEnabled = false
            offscreenPageLimit = tabs.size
        }

        TabLayoutMediator(tlStock, pager) { tab, position ->
            tab.text = tabs[position]
        }.attach()


    }



}