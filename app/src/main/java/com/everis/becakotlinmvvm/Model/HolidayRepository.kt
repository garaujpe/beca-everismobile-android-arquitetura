package com.everis.becakotlinmvvm.Model

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.everis.becakotlinmvvm.Api.RetrofitClient
import com.everis.becakotlinmvvm.HolidayModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HolidayRepository {

    companion object{
        const val RESPONSE = "onResponse response="
        const val RESPONSE_SIZE = "onResponse response.size="
        const val FAILURE = "onFailure call="
    }

    val TAG: String = javaClass.simpleName

    fun fetchHolidays(): MutableLiveData<List<HolidayModel>> {
        val holidayMutableList: MutableLiveData<List<HolidayModel>> = MutableLiveData()

        val apiInterface = RetrofitClient.retrofitApi()

        apiInterface.getHolidays().enqueue(object : Callback<List<HolidayModel>> {
            override fun onResponse(
                call: Call<List<HolidayModel>>,
                responseHoliday: Response<List<HolidayModel>>
            ) {
                Log.e(TAG, RESPONSE + responseHoliday.toString())

                if (responseHoliday.isSuccessful) {
                    Log.e(TAG, RESPONSE_SIZE + responseHoliday.body()?.size)

                    if (responseHoliday.body() != null && responseHoliday.body()?.size!! > 0) {
                        holidayMutableList.value = responseHoliday.body()!!
                    }
                }
            }

            override fun onFailure(call: Call<List<HolidayModel>>, t: Throwable) {
                Log.e(TAG, FAILURE + call.toString())
            }
        })

        return holidayMutableList
    }
}