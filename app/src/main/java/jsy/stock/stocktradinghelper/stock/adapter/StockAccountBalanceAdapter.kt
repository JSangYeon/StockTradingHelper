package jsy.stock.stocktradinghelper.stock.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.ObservableField
import androidx.databinding.ObservableInt
import androidx.recyclerview.widget.RecyclerView
import jsy.stock.stocktradinghelper.databinding.ItemStockAccountBalanceBinding
import jsy.stock.stocktradinghelper.room.Stock
import jsy.stock.stocktradinghelper.viewmodel.StockViewModel

class StockAccountBalanceAdapter(private val stockViewModel: StockViewModel) :
        RecyclerView.Adapter<StockAccountBalanceAdapter.Holder>() {

    private val tag = "StockAccountBalanceAdapter"
    private lateinit var stockList : List<Stock>

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StockAccountBalanceAdapter.Holder {
        ItemStockAccountBalanceBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
        ).apply {
            viewModel = stockViewModel

            Log.d(tag, "viewCreate")
            return Holder(this)
        }

    }

    override fun onBindViewHolder(holder: StockAccountBalanceAdapter.Holder, position: Int) {
        holder.setIsRecyclable(false)
        holder.itemName.set(stockList[position].stockName)
        holder.itemPrice.set(stockList[position].lifeSpan.toString())

        Log.d(tag,"lifeSpan : ${holder.itemPrice.get()}")
        holder.bind()
    }

    override fun getItemCount(): Int {

        var size = 0
        if(stockViewModel.stockList.value!=null)
        {
            stockList = stockViewModel.stockList.value!!
            size = stockList.size
        }

        return size
    }


    inner class Holder(private var binding: ItemStockAccountBalanceBinding) : RecyclerView.ViewHolder(binding.root) {

        val itemName = ObservableField<String>()
        val itemPrice = ObservableField<String>()

        fun bind(){
            binding.apply {
                viewName = itemName
                viewPrice = itemPrice
            }
        }
    }

}
