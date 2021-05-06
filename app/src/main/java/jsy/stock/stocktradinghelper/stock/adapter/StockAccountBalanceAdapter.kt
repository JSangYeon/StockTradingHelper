package jsy.stock.stocktradinghelper.stock.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.findViewTreeLifecycleOwner
import androidx.recyclerview.widget.RecyclerView
import io.reactivex.rxjava3.disposables.CompositeDisposable
import jsy.stock.stocktradinghelper.databinding.ItemStockAccountBalanceBinding
import jsy.stock.stocktradinghelper.room.Stock
import jsy.stock.stocktradinghelper.viewmodel.StockViewModel

class StockAccountBalanceAdapter(private val _stockViewModel: StockViewModel, private val disposable: CompositeDisposable) :
        RecyclerView.Adapter<StockAccountBalanceAdapter.Holder>() {

    private val tag = "StockAccountBalanceAdapter"
    private lateinit var parentViewGroup: ViewGroup
    private lateinit var stockList: ArrayList<Stock>
    private lateinit var parentLifecycleOwner: LifecycleOwner

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StockAccountBalanceAdapter.Holder {
        parentViewGroup = parent

        parentLifecycleOwner = parent.findViewTreeLifecycleOwner()!!
        ItemStockAccountBalanceBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
        ).apply {
            viewModel = _stockViewModel

            Log.d(tag, "viewCreate")
            return Holder(this)
        }

    }

    override fun onBindViewHolder(holder: StockAccountBalanceAdapter.Holder, position: Int) {
        holder.setIsRecyclable(false)

        Log.d(tag, "onBindViewHolder")
        holder.bind(position)
    }

    override fun getItemCount(): Int {

        var size = 0

        if (_stockViewModel.stockList.value != null) {
            stockList = _stockViewModel.stockList.value!!
            size = stockList.size
        }

        return size
    }


    inner class Holder(private var binding: ItemStockAccountBalanceBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(position: Int) {

            val stock = _stockViewModel.stockList.value!![position]

            binding.apply {

                tvStockName.text = stock.stockName
                tvStockAveragePrice.text = stock.averagePrice.toString()
                tvStockAccount.text = stock.account.toString()

                btnReviseStock.setOnClickListener {
                    _stockViewModel.reviseDialog(root, stock)
                }

                btnRemoveStock.setOnClickListener {
                    _stockViewModel.removeDialog(root, stock)
                }
            }
        }
    }


}
