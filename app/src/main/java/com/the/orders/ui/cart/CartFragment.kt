package com.the.orders.ui.cart

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.the.orders.databinding.FragmentCartBinding


class CartFragment : Fragment() {

    private var _binding: FragmentCartBinding? = null

    lateinit var recylerviewAdapter: AdapterCart

    lateinit var   viewModel: CartViewModel

    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCartBinding.inflate(inflater, container, false)
        val root: View = binding.root

        viewModel = ViewModelProvider(this).get(CartViewModel::class.java)

        binding.recyclerview.layoutManager = LinearLayoutManager(context)
        val decoration = DividerItemDecoration(context, DividerItemDecoration.HORIZONTAL)


        binding.recyclerview.addItemDecoration(decoration)
        binding.recyclerview.setHasFixedSize(true)

        recylerviewAdapter = AdapterCart()


        binding.recyclerview.adapter = recylerviewAdapter

        recylerviewAdapter.onItemClick = {

            val builder = AlertDialog.Builder(requireContext())
            builder.setTitle("Buy product")
            builder.setPositiveButton("confirm") { dialog, which ->
                viewModel.saveProducts(it)

                Toast.makeText(context, "you have confirmed order ", Toast.LENGTH_SHORT).show()
            }

            builder.setNegativeButton("cansel") { dialog, which ->
                Toast.makeText(context, "you have canseled order", Toast.LENGTH_SHORT).show()
            }
            builder.show()



        }

        recylerviewAdapter.deleteClick = {

            viewModel.deleteProduct(it)

        }



        viewModel.resultLiveData.observe(viewLifecycleOwner) {
            if (it == null) {
                Toast.makeText(context, "no result found", Toast.LENGTH_LONG).show()

            } else {


                recylerviewAdapter.data = it


                recylerviewAdapter.notifyDataSetChanged()

            }

        }


        viewModel.getProductList()


        return root


    }
}