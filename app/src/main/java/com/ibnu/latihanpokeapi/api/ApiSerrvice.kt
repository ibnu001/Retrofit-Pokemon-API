package com.ibnu.latihanpokeapi.api

import com.ibnu.latihanpokeapi.PokemonResponse
import retrofit2.Call
import retrofit2.http.*

interface ApiSerrvice {

    @GET("api/v2/pokemon-form/?offset=0&limit=1323}")
    fun getPokemon(
        // gk bisa gunain path klo terlalu spesifik seperti limit={batas}
//        @Path("offset") offset: String,
//        @Path("batas") batas: String,
    ): Call<PokemonResponse>
}