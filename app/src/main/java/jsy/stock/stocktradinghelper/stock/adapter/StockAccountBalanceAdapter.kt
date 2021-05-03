package jsy.stock.stocktradinghelper.stock.adapter

import android.app.AlertDialog
import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.RatingBar
import androidx.databinding.ObservableField
import androidx.databinding.ObservableInt
import androidx.recyclerview.widget.RecyclerView
import jsy.stock.stocktradinghelper.R
import jsy.stock.stocktradinghelper.databinding.ItemStockAccountBalanceBinding
import jsy.stock.stocktradinghelper.room.Stock
import jsy.stock.stocktradinghelper.viewmodel.StockViewModel
import kotlin.coroutines.coroutineContext

class StockAccountBalanceAdapter(private val stockViewModel: StockViewModel) :
        RecyclerView.Adapter<StockAccountBalanceAdapter.Holder>() {

    private val tag = "StockAccountBalanceAdapter"
    private lateinit var stockList : List<Stock>
    private lateinit var context : Context

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StockAccountBalanceAdapter.Holder {
        context = parent.context
        ItemStockAccountBalanceBinding.inflate(
                LayoutInflater.from(context),
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

                btnAddStock.setOnClickListener{

                    Log.d(tag, "btnAddStock : , viewName : ${itemName.get()}")
                    val builder = AlertDialog.Builder(context)
                    val dialogView = LayoutInflater.from(context).inflate(R.layout.custom_dialog, null)
                    val dialogText = dialogView.findViewById<EditText>(R.id.dialogEt)
                    val dialogRatingBar = dialogView.findViewById<RatingBar>(R.id.dialogRb)

                    builder.setView(dialogView)
                            .setPositiveButton("확인") { dialogInterface, i ->
                                Log.d(tag, "text : ${dialogText.text.toString()}, ratingBar : ${dialogRatingBar.rating}")
                                /* 확인일 때 main의 View의 값에 dialog View에 있는 값을 적용 */
                            }
                            .setNegativeButton("취소") { dialogInterface, i ->
                                /* 취소일 때 아무 액션이 없으므로 빈칸 */
                            }
                            .show()
                }

                btnRemoveStock.setOnClickListener{
                    Log.d(tag, "btnRemoveStock : , viewName : ${itemPrice.get()}")
                }
            }
        }
    }



}
