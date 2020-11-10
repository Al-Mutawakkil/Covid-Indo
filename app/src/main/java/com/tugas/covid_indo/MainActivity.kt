package com.tugas.covid_indo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.tugas.covid_indo.model.DataItem
import com.tugas.covid_indo.model.ResponseProvinsi
import com.tugas.covid_indo.network.ApiService
import com.tugas.covid_indo.network.RetrofitBuilder.retrofit
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.text.DecimalFormat
import java.text.NumberFormat

class MainActivity : AppCompatActivity() {
    
    companion object {
        lateinit var adapterProvinsi: AdapterProvinsi
    }
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        
        getProvinsi()
    }

    private fun getProvinsi() {
        val api = retrofit.create(ApiService::class.java)
        api.getAllProvinsi().enqueue(object: Callback<ResponseProvinsi> {
            override fun onFailure(call: Call<ResponseProvinsi>, t: Throwable) {
                progress_bar.visibility =View.GONE
            }

            override fun onResponse(call: Call<ResponseProvinsi>, response: Response<ResponseProvinsi>) {
                rv_provinsi.apply {
                    setHasFixedSize(true)
                    layoutManager = LinearLayoutManager(this@MainActivity)
                    progress_bar?.visibility = View.GONE
                    adapterProvinsi = AdapterProvinsi(response.body()!!.data as ArrayList<DataItem>){}
                    adapter = adapterProvinsi
                }
            }
        })
    }
}