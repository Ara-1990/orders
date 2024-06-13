package com.the.orders.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.the.orders.databinding.FragmentHomeBinding
import com.the.orders.remote.ProductsModel
import kotlinx.coroutines.launch

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null

    lateinit var recylerviewAdapter: AdapterProducts

    lateinit var   viewModel: HomeViewModel

    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root

        viewModel = ViewModelProvider(this).get(HomeViewModel::class.java)


        binding.recyclerview.layoutManager = LinearLayoutManager(context)
        val decoration = DividerItemDecoration(context, DividerItemDecoration.HORIZONTAL)


        binding.recyclerview.addItemDecoration(decoration)
        binding.recyclerview.setHasFixedSize(true)

        recylerviewAdapter = AdapterProducts()


        binding.recyclerview.adapter = recylerviewAdapter


        recylerviewAdapter.onItemClick = {

            val builder = AlertDialog.Builder(requireContext())
            builder.setTitle("Send to cart")
            builder.setPositiveButton("yes") { dialog, which ->
                viewModel.save(it)
                Toast.makeText(context, "you have sent order to cart", Toast.LENGTH_SHORT).show()
            }

            builder.setNegativeButton("no") { dialog, which ->
            }
            builder.show()



        }



        viewModel.resultLiveData.observe(viewLifecycleOwner) {
            if (it == null) {
                Toast.makeText(context, "no result found", Toast.LENGTH_LONG).show()

            } else {

                recylerviewAdapter.setListItems(it.products)
                recylerviewAdapter.notifyDataSetChanged()

            }

        }
        viewModel.getProductList()


        viewModel.searchLiveData.observe(viewLifecycleOwner, Observer<ProductsModel?> {

            recylerviewAdapter.setListItems(it.products)



        })


        binding.search.setOnQueryTextListener(object : SearchView.OnQueryTextListener {

            override fun onQueryTextSubmit(query: String?): Boolean {

                if (query != null) {
                    lifecycleScope.launch {
                        viewModel.searchProduct(query)

                    }

                }
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                if (newText != null) {
                    viewModel.searchProduct(newText)


                }

                return true

            }

        })


        return root
    }

}