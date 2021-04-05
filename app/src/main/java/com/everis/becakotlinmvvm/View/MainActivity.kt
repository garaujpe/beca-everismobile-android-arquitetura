package com.everis.becakotlinmvvm.View

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import com.everis.becakotlinmvvm.HolidayModel
import com.everis.becakotlinmvvm.ViewModel.HolidayViewModel
import com.everis.becakotlinmvvm.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    companion object{
        const val MSG = "observe onChanged()="
    }

    val TAG = javaClass.simpleName

    lateinit var holidayAdapter: HolidayAdapter
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initUI()
        initObserver()

        loadingVisibility(true)
    }

    private fun initObserver(){
        val holidayViewModel = HolidayViewModel()
        holidayViewModel.getHolidays().observe(this,
            { holidayList ->
                Log.e(TAG, MSG + holidayList?.size )
                UpadateList(holidayList!!)
                loadingVisibility(false)
            })
    }

    private fun UpadateList(List: List<HolidayModel>){
        holidayAdapter.UpdateAdapter(List)
    }

    private fun initUI() {
        val layoutManagerHoliday = LinearLayoutManager(this)
        binding.rvHolidayList.apply {
            setHasFixedSize(true);
            layoutManager = layoutManagerHoliday
            itemAnimator = DefaultItemAnimator()
        }
        holidayAdapter = HolidayAdapter()
        binding.rvHolidayList.adapter = holidayAdapter
    }

    private fun loadingVisibility(loading: Boolean){
        binding.progressBar.visibility = if (loading) View.VISIBLE else View.GONE
    }

}