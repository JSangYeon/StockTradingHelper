package jsy.stock.stocktradinghelper.stock.activity

import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import com.google.android.material.tabs.TabLayoutMediator
import jsy.stock.stocktradinghelper.R
import jsy.stock.stocktradinghelper.base.BaseActivity
import jsy.stock.stocktradinghelper.databinding.ActivityMainBinding
import jsy.stock.stocktradinghelper.stock.adapter.MainViewPagerAdapter
import jsy.stock.stocktradinghelper.viewmodel.SettingViewModel
import jsy.stock.stocktradinghelper.viewmodel.StockViewModel


class MainActivity : BaseActivity<ActivityMainBinding>(R.layout.activity_main) {

    private val _stockViewModel: StockViewModel by viewModels()
    private val _settingViewModel: SettingViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding.apply {
            lifecycleOwner = this@MainActivity
            main = this@MainActivity
            stockViewModel = _stockViewModel

            val pi = packageManager.getPackageInfo(packageName, 0)
            Log.d("Main", "pi : $pi, pi.code : ${pi.longVersionCode}, pi.name : ${pi.versionName}")

            val sharedPreferences = this@MainActivity.getPreferences(MODE_PRIVATE)
            _settingViewModel.init(sharedPreferences)

//            val lastVersionCode = sharedPreferences.getLong("lastVersion",0)
//            if(lastVersionCode != pi.longVersionCode)
//            {
//                 Log.d("Main","versionCode 갱신")
//
//                val editor = sharedPreferences.edit()
//                editor.putLong("lastVersion", pi.longVersionCode)
//                editor.apply()
//            }
//
//
//            Log.d("Main","lastVersionCode : $lastVersionCode")

            initView()

        }
    }

    private fun initView() {

        initTab()

    }


    private fun initTab() {
        val pager = binding.pager
        val tlStock = binding.tlStock
        val tabs: Array<String> = resources.getStringArray(R.array.list_tab)

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