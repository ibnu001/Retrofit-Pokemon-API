package com.ibnu.latihanpokeapi.api

import com.ibnu.latihanpokeapi.PokemonResponse
import com.ibnu.latihanpokeapi.ResultsItem
import retrofit2.Call
import retrofit2.http.*

interface ApiSerrvice {
    //?offset=0&limit=905
    @GET("api/v2/pokemon")
    fun getPokemon(
        @Query("offset") offset: Int,
        @Query("limit") limit: Int,
    ): Call<PokemonResponse>

    @GET("api/v2/pokemon/{id}")
    fun getPoke(
        @Path("id") id: String,
    ): Call<ResultsItem>

}