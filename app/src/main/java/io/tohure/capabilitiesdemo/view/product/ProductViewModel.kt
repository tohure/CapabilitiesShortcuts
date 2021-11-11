package io.tohure.capabilitiesdemo.view.product

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import io.tohure.capabilitiesdemo.data.ProductRepository
import io.tohure.capabilitiesdemo.model.Product
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class ProductViewModel(private val repository: ProductRepository) : ViewModel() {

    private var _productList = MutableLiveData<List<Product>>()
    val productList: LiveData<List<Product>>
        get() = _productList

    private var _categoryList = MutableLiveData<List<String>>()
    val categoryList: LiveData<List<String>>
        get() = _categoryList

    private val exceptionHandler = CoroutineExceptionHandler { _, throwable ->
        onError("Exception handled: ${throwable.localizedMessage}")
    }

    fun getProducts() {
        viewModelScope.launch(Dispatchers.IO + exceptionHandler) {
            val response = repository.getProducts()
            withContext(Dispatchers.Main) {
                if (response.isSuccessful) {
                    _productList.postValue(response.body())
                } else {
                    onError("Error : ${response.message()} ")
                }
            }
        }
    }

    fun getCategories() {
        viewModelScope.launch(Dispatchers.IO + exceptionHandler) {
            val response = repository.getCategories()
            withContext(Dispatchers.Main) {
                if (response.isSuccessful) {
                    _categoryList.postValue(response.body())
                } else {
                    onError("Error : ${response.message()} ")
                }
            }
        }
    }

    fun getProductsByCategory(category: String) {
        viewModelScope.launch(Dispatchers.IO + exceptionHandler) {
            val response = repository.getProductsByCategory(category)
            withContext(Dispatchers.Main) {
                if (response.isSuccessful) {
                    _productList.postValue(response.body())
                } else {
                    onError("Error : ${response.message()} ")
                }
            }
        }
    }

    private fun onError(message: String) {
        Log.d("Thr", message)
    }

}