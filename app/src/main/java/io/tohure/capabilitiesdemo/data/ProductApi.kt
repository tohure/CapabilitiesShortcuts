package io.tohure.capabilitiesdemo.data

import io.tohure.capabilitiesdemo.model.Product
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface ProductApi {

    @GET("/products")
    suspend fun getAllProducts(): Response<List<Product>>

    @GET("/products/categories")
    suspend fun getCategories(): Response<List<String>>

    @GET("/products/category/{categoryId}")
    suspend fun getProductsByCategory(
        @Path("categoryId") categoryId: String
    ): Response<List<Product>>

}
