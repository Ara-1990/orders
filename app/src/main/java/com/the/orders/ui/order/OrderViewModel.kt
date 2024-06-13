package com.the.orders.ui.order

import android.app.Application
import androidx.lifecycle.*
import com.the.orders.db.Db
import com.the.orders.db.OrderEntities
import com.the.orders.repository.RepositoryOrder
import kotlinx.coroutines.launch

class OrderViewModel (application: Application) : AndroidViewModel(application) {

     var repositoryorder: RepositoryOrder

    private val resultMutable = MutableLiveData<List<OrderEntities>?>()
    val resultLiveData: LiveData<List<OrderEntities>?> = resultMutable

    init {

        val daoOrders = Db.create(context = application).daoOrders
        repositoryorder = RepositoryOrder(daoOrders)
    }

    fun getOrders() {

        viewModelScope.launch {
            val result = repositoryorder.getOrders()
            resultMutable.value = result


        }


    }


}