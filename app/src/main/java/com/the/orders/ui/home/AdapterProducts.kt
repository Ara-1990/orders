package com.the.orders.ui.home

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.the.orders.R
import com.the.orders.databinding.ItemProductBinding
import com.the.orders.remote.ProductsModelList

class AdapterProducts: RecyclerView.Adapter<AdapterProducts.Productholder>() {


    private val differ = AsyncListDiffer(this, Comparator())


    fun setListItems(items: List<ProductsModelList>?) {
        differ.submitList(items)
    }
    var onItemClick: ((ProductsModelList) -> Unit) ? = null




    inner class Productholder(view: View): RecyclerView.ViewHolder(view){


        var addCart = view.findViewById<TextView>(R.id.add_cart)

        private val binding = ItemProductBinding.bind(view)



        fun bind(productModel: ProductsModelList) = with(binding){

            name.text = productModel.title
            description.text = productModel.description

        }

    }

    class Comparator: DiffUtil.ItemCallback<ProductsModelList>(){
        override fun areItemsTheSame(oldItem: ProductsModelList, newItem: ProductsModelList): Boolean {
            return oldItem.id == newItem.id
        }

        @SuppressLint("DiffUtilEquals")
        override fun areContentsTheSame(oldItem: ProductsModelList, newItem: ProductsModelList): Boolean {
            return oldItem == newItem
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Productholder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_product, parent, false)
        return Productholder(view)

    }

    override fun onBindViewHolder(holder: Productholder, position: Int) {
        holder.bind(differ.currentList[position])
        val item = differ.currentList[position]


        holder.addCart.setOnClickListener {
            onItemClick?.invoke(item)
        }




    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }


}
