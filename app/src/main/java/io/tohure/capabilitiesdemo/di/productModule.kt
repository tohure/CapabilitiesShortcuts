package io.tohure.capabilitiesdemo.di

import io.tohure.capabilitiesdemo.data.ProductApi
import io.tohure.capabilitiesdemo.data.ProductRepository
import io.tohure.capabilitiesdemo.view.product.ProductViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit

val productModule = module {
    single { provideProductApi(get()) }
    factory { provideProductRepository(get()) }
    viewModel { ProductViewModel(get()) }
}

fun provideProductRepository(productApi: ProductApi) = ProductRepository(productApi)

fun provideProductApi(retrofit: Retrofit): ProductApi = retrofit.create(ProductApi::class.java)