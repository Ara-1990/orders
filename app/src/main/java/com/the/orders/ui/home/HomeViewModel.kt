package com.the.orders.ui.home

import android.app.Application
import androidx.lifecycle.*
import com.the.orders.db.Db
import com.the.orders.remote.ProductsModel
import com.the.orders.remote.ProductsModelList
import com.the.orders.remote.RemoteInctsnce
import com.the.orders.remote.RemoteServise
import com.the.orders.repository.RepositoryRemote
import com.the.orders.repository.RepositoryRemoteSendCart
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class HomeViewModel (application: Application): AndroidViewModel(application) {


     var repository: RepositoryRemote
    private  val resultMutable = MutableLiveData<ProductsModel>()
    val resultLiveData: LiveData<ProductsModel> = resultMutable

     var repositoryData: RepositoryRemoteSendCart

    private val resualtsearch = MutableLiveData<ProductsModel>()
    val searchLiveData: LiveData<ProductsModel> = resualtsearch



    init {
        val retroInctance = RemoteInctsnce.getRetroInctance().create(RemoteServise::class.java)
        repository = RepositoryRemote(retroInctance)

        val dao = Db.create(context = application).daoCart
        repositoryData = RepositoryRemoteSendCart(dao)



    }

    fun getProductList() {

        viewModelScope.launch {
            val result = repository.getProducts()
            resultMutable.value = result




        }


    }

    fun save(product: ProductsModelList){
        viewModelScope.launch(Dispatchers.IO){
            repositoryData.saveProduct(product)

        }

    }

         fun searchProduct(searchText: String) {


        viewModelScope.launch {
            val retroInctance = RemoteInctsnce.getRetroInctance().create(RemoteServise::class.java)
            val cal = retroInctance.serchProducts(searchText)
            resualtsearch.value = cal
        }

     }


}