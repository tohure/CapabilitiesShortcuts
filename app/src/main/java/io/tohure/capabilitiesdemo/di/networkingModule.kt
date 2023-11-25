package io.tohure.capabilitiesdemo.di

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val networkingModule = module {
    single {
        HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }
    }
    single { provideOkHttpClient(get()) }
    single { provideRetrofit(get()) }
}

fun provideOkHttpClient(interceptor: HttpLoggingInterceptor) =
    OkHttpClient.Builder()
        .addInterceptor(interceptor)
        .build()

fun provideRetrofit(client: OkHttpClient): Retrofit {
    val builder = Retrofit.Builder()
        .baseUrl("https://fakestoreapi.com")
        .addConverterFactory(GsonConverterFactory.create())

    return builder.client(client).build()
}