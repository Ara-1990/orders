package com.the.orders.ui.cart

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.the.orders.R
import com.the.orders.databinding.ItemCartBinding
import com.the.orders.db.CartEntities

class AdapterCart: RecyclerView.Adapter<AdapterCart.Productholder>() {


    var data = mutableListOf<CartEntities>()


    var onItemClick: ((CartEntities) -> Unit)? = null

    var deleteClick: ((CartEntities) -> Unit)? = null


    inner class Productholder(view: View) : RecyclerView.ViewHolder(view) {



        val order = view.findViewById<TextView>(R.id.order)

        private val binding = ItemCartBinding.bind(view)



        fun bind(cartModel: CartEntities, index: Int) = with(binding) {

            name.text = cartModel.name
            description.text = cartModel.description

            delete.setOnClickListener {
                deleteItem(index)
                deleteClick?.invoke(cartModel)
            }
        }


    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Productholder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_cart, parent, false)
        return Productholder(view)

    }

    override fun onBindViewHolder(holder: Productholder, position: Int) {
        val item = data[position]
        holder.bind(data[position], position)



        holder.order.setOnClickListener {

            onItemClick?.invoke(item)


        }


    }


    override fun getItemCount(): Int {

        return data.size

    }


    fun deleteItem(index: Int) {
        data.removeAt(index)
        notifyDataSetChanged()
    }
}