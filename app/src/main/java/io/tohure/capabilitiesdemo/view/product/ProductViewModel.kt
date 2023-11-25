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
import kotlinx.coroutines.async
import kotlinx.coroutines.launch

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
        viewModelScope.launch {

            val response = async(Dispatchers.IO + exceptionHandler) {
                repository.getProducts()
            }.await()

            if (response.isSuccessful) {
                _productList.postValue(response.body())
            } else {
                onError("Error : ${response.message()} ")
            }

        }
    }

    fun getCategories() {
        viewModelScope.launch {

            val response = async(Dispatchers.IO + exceptionHandler) {
                repository.getCategories()
            }.await()

            if (response.isSuccessful) {
                _categoryList.postValue(response.body())
            } else {
                onError("Error : ${response.message()} ")
            }
        }
    }

    fun getProductsByCategory(category: String) {
        viewModelScope.launch {

            val response = async(Dispatchers.IO + exceptionHandler) {
                repository.getProductsByCategory(category)
            }.await()

            if (response.isSuccessful) {
                _productList.postValue(response.body())
            } else {
                onError("Error : ${response.message()} ")
            }

        }
    }

    private fun onError(message: String) {
        Log.d("Thr", message)
    }

}