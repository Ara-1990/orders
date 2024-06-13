package com.the.orders.ui.order

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.the.orders.databinding.FragmentOrderBinding

class OrderFragment : Fragment() {

    private var _binding: FragmentOrderBinding? = null


    lateinit var recylerviewAdapter: AdapterOrders

    lateinit var   viewModel: OrderViewModel

    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentOrderBinding.inflate(inflater, container, false)
        val root: View = binding.root

        // Inflate the layout for this fragment
        viewModel = ViewModelProvider(this).get(OrderViewModel::class.java)

        binding.recyclerview.layoutManager = LinearLayoutManager(context)
        val decoration = DividerItemDecoration(context, DividerItemDecoration.HORIZONTAL)


        binding.recyclerview.addItemDecoration(decoration)
        binding.recyclerview.setHasFixedSize(true)

        recylerviewAdapter = AdapterOrders()


        binding.recyclerview.adapter = recylerviewAdapter

        viewModel.resultLiveData.observe(viewLifecycleOwner) {
            if (it == null) {
                Toast.makeText(context, "no result found", Toast.LENGTH_LONG).show()

            } else {

                recylerviewAdapter.setListItems(it)
                recylerviewAdapter.notifyDataSetChanged()

            }

        }

        viewModel.getOrders()


        return root

    }

}


