package com.everis.becakotlinmvvm.Api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitClient {
    companion object {

        const val BASE_URL = "https://date.nager.at/api/v2/"

        fun getRetrofitInstance(path : String) : Retrofit {
            return Retrofit.Builder()
                .baseUrl(path)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        }
        fun retrofitApi():ApiInterface = getRetrofitInstance(BASE_URL).create(ApiInterface::class.java)
    }
}