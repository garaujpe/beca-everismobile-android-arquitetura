package com.everis.becakotlinmvvm.Api

import com.everis.becakotlinmvvm.HolidayModel
import retrofit2.Call
import retrofit2.http.GET

interface ApiInterface {

    companion object{
        const val ROTA = "PublicHolidays/2021/br"
    }
    @GET(ROTA)
    fun getHolidays(): Call<List<HolidayModel>>
}