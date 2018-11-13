package io.tickr.data.network

import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class ServiceProvider {

    companion object {

        private val retrofit: Retrofit by lazy {
            Retrofit.Builder()
                .baseUrl("https://api.iextrading.com/1.0/")
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build()
        }

        fun <T> createService(clazz: Class<T>): T {
            return retrofit.create<T>(clazz)
        }

    }
}