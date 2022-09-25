package com.ibnu.latihanpokeapi

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.ibnu.latihanpokeapi.adapter.PokemonAdapter
import com.ibnu.latihanpokeapi.api.ApiConfig
import com.ibnu.latihanpokeapi.databinding.ActivityListPokemonBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ListPokemonActivity : AppCompatActivity() {

    private lateinit var binding: ActivityListPokemonBinding

    companion object {
        private const val TAG = "ListPokemonActivity"
//        const val ID = "id"
    }

    private var offset: Int = 0
    private val limit: Int = 20

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityListPokemonBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val layoutManager = LinearLayoutManager(this)
        binding.rvListPokemon.layoutManager = layoutManager

        // garis hitam pemisah item
//        val itemDescription = DividerItemDecoration(this, layoutManager.orientation)
//        binding.rvListPokemon.addItemDecoration(itemDescription)

        findPokemon()

        binding.btnNext.setOnClickListener {
            if (offset < 1140) {
                offset += 20
            }

            findPokemon()

            Log.e(TAG, "onCreate: off : $offset, lim : $limit")
        }

        binding.btnPrevious.setOnClickListener {
            if (offset != 0) {
                offset -= 20
            }

            findPokemon()

            Log.e(TAG, "onCreate: off : $offset, lim : $limit")
        }
    }

    private fun findPokemon() {
        showLoading(true)

        val client = ApiConfig.getApiService().getPokemon(offset, limit)

        client.enqueue(object : Callback<PokemonResponse> {
            override fun onResponse(
                call: Call<PokemonResponse>,
                response: Response<PokemonResponse>,
            ) {
                showLoading(false)

                if (response.isSuccessful) {
                    val responseBody = response.body()

                    if (responseBody != null) {
                        setPokemonData(responseBody.results)

                    }
                } else {
                    Log.e(TAG, "onFailure: ${response.message()}")
                }
            }

            override fun onFailure(call: Call<PokemonResponse>, t: Throwable) {
                showLoading(false)
                Log.e(TAG, "onFailure: ${t.message}")
            }
        })
    }

    private fun setPokemonData(pokemonList: List<ResultsItem>) {
        val listItem = ArrayList<String>()

        for (pokemon in pokemonList) {
            listItem.add(
                """
                    ${pokemon.name}
                    - detail : ${pokemon.url}
                """.trimIndent()
            )
        }

        // untuk sort item nya
//        listItem.sort()

        val adapter = PokemonAdapter(listItem)

        binding.rvListPokemon.adapter = adapter
    }

    private fun showLoading(isLoading: Boolean) {
        if (isLoading) {
            binding.progressBar.visibility = View.VISIBLE
        } else {
            binding.progressBar.visibility = View.GONE
        }
    }
}