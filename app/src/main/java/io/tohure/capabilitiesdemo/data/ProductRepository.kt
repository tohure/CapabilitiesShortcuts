package io.tohure.capabilitiesdemo.data

class ProductRepository(private val productApi: ProductApi) {

    suspend fun getProducts() = productApi.getAllProducts()
    suspend fun getCategories() = productApi.getCategories()
    suspend fun getProductsByCategory(category: String) = productApi.getProductsByCategory(category)

}