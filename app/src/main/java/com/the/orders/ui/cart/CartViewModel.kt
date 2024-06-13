package com.the.orders.ui.cart

import android.app.Application
import androidx.lifecycle.*
import com.the.orders.db.CartEntities
import com.the.orders.db.Db
import com.the.orders.repository.RepositoryCart
import com.the.orders.repository.RepositoryOrder
import kotlinx.coroutines.launch

class CartViewModel (application: Application) : AndroidViewModel(application) {

     var repository: RepositoryCart
     var repositoryorder: RepositoryOrder

    val resultMutable = MutableLiveData<MutableList<CartEntities>?>()
    val resultLiveData: LiveData<MutableList<CartEntities>?> = resultMutable

    init {
        val dao = Db.create(context = application).daoCart
        repository = RepositoryCart(dao)

        val daoOrders = Db.create(context = application).daoOrders
        repositoryorder = RepositoryOrder(daoOrders)
    }

    fun getProductList() {

        viewModelScope.launch {
            val result = repository.getProducts()
            resultMutable.value = result


        }


    }

    fun saveProducts(product:CartEntities){
        viewModelScope.launch {
            repositoryorder.saveProduct(product)
        }
    }

    fun deleteProduct(product:CartEntities){
        viewModelScope.launch {

            repository.deleteItem(product)
        }
    }

}