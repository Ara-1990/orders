package com.the.orders.ui.order

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.the.orders.R
import com.the.orders.databinding.ItemOrderBinding
import com.the.orders.db.OrderEntities

class AdapterOrders : RecyclerView.Adapter<AdapterOrders.Productholder>() {


    private val differ = AsyncListDiffer(this, Comparator())


    fun setListItems(items: List<OrderEntities>?) {
        differ.submitList(items)
    }


    inner class Productholder(view: View) : RecyclerView.ViewHolder(view) {


        private val binding = ItemOrderBinding.bind(view)


        fun bind(cartModel: OrderEntities) = with(binding) {

            name.text = cartModel.name
            description.text = cartModel.description

        }

    }

    class Comparator : DiffUtil.ItemCallback<OrderEntities>() {
        override fun areItemsTheSame(oldItem: OrderEntities, newItem: OrderEntities): Boolean {
            return oldItem.id == newItem.id
        }

        @SuppressLint("DiffUtilEquals")
        override fun areContentsTheSame(oldItem: OrderEntities, newItem: OrderEntities): Boolean {
            return oldItem == newItem
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Productholder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_order, parent, false)
        return Productholder(view)

    }

    override fun onBindViewHolder(holder: Productholder, position: Int) {
        holder.bind(differ.currentList[position])


    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }


}