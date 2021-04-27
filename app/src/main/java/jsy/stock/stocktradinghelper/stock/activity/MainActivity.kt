package jsy.stock.stocktradinghelper.stock.activity

import android.os.Bundle
import android.util.Log
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.viewModels
import com.google.android.material.tabs.TabItem
import com.google.android.material.tabs.TabLayout
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.schedulers.Schedulers
import jsy.stock.stocktradinghelper.R
import jsy.stock.stocktradinghelper.base.BaseActivity
import jsy.stock.stocktradinghelper.databinding.ActivityMainBinding
import jsy.stock.stocktradinghelper.room.Stock
import jsy.stock.stocktradinghelper.room.StockDB
import jsy.stock.stocktradinghelper.viewmodel.StockViewModel


class MainActivity :  BaseActivity<ActivityMainBinding>(R.layout.activity_main) {


    private val _stockViewModel: StockViewModel by viewModels()
    private val disposable = CompositeDisposable()


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

        val stockDB = StockDB.getInstance(this)!!


        val newStock = Stock()
        newStock.stockName = "jsy"
        newStock.lifeSpan = 12345
        newStock.origin = "text"


        disposable.add(
            stockDB.stockDao().insert(newStock)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ Log.d("stock", "success") },
                    { error -> Log.e("stock", "error : ${error.printStackTrace()}") })
        )

    }



    private fun initTab()
    {

        val tabs : Array<String> = resources.getStringArray(R.array.list_tab)

        if (binding.tlStock.tabCount == 0) {
            for (i in tabs.indices) {
                val item = TabItem(this)
                val layoutParams = ViewGroup.LayoutParams(
                    ViewGroup.LayoutParams.WRAP_CONTENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT
                )
                binding.tlStock.addView(item, layoutParams)
            }
        }

//        for (i in tabs.indices) {
//            val temp: TabLayout.Tab = binding.tablayout.getTabAt(i)
//            if (temp != null) {
//                temp.setText(tabs.get(i))
//            }
//        }
//
//        tabLayout.addOnTabSelectedListener(object : OnTabSelectedListener() {
//            fun onTabSelected(tab: CustomTabLayout.Tab) {
//                Toast.makeText(this@MainActivity, tab.getText(), Toast.LENGTH_SHORT).show()
//            }
//
//            fun onTabUnselected(tab: CustomTabLayout.Tab?) {}
//            fun onTabReselected(tab: CustomTabLayout.Tab?) {}
//        })
    }



}