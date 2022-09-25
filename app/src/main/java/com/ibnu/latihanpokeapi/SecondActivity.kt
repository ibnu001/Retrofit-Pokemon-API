package com.ibnu.latihanpokeapi

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.bumptech.glide.Glide
import com.google.android.material.internal.ManufacturerUtils
import com.ibnu.latihanpokeapi.api.ApiConfig
import com.ibnu.latihanpokeapi.databinding.ActivitySecondBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SecondActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySecondBinding

    companion object {
        private const val TAG = "SecondActivity"
        const val ID = "id"
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivitySecondBinding.inflate(layoutInflater)
        setContentView(binding.root)

        tess()


    }

    private fun tess() {
        val id = intent.getStringExtra(ID).toString()
        Log.e(TAG, "tess222: $id")
        val client = ApiConfig.getApiService().getPoke(id)

        client.enqueue(object : Callback<ResultsItem> {
            override fun onResponse(call: Call<ResultsItem>, response: Response<ResultsItem>) {
                if (response.isSuccessful) {
                    val responseBody = response.body()

                    if (responseBody != null) {
                        val name = responseBody.name
                        val image = responseBody.sprites.frontDefault

                        Log.e(TAG, "onResponse3: $name, iv: $image")
                        binding.tvUwu.text = name

                        Glide.with(this@SecondActivity)
                            .load(image)
                            .centerCrop()
                            .into(binding.ivPokemon)
                    }
                } else {
                    Log.e(TAG, "onFailure: ${response.message()}")
                }
            }

            override fun onFailure(call: Call<ResultsItem>, t: Throwable) {
                Log.e(TAG, "onFailure: ${t.message}")
            }
        })

    }
}