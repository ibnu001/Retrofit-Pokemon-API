package com.ibnu.latihanpokeapi

import com.google.gson.annotations.SerializedName

data class PokemonResponse(

	@field:SerializedName("results")
	val results: List<ResultsItem>,
)

data class ResultsItem(

	@field:SerializedName("name")
	val name: String,

	@field:SerializedName("url")
	val url: String,

	@field:SerializedName("sprites")
	val sprites: Sprites
)

data class Sprites(
    @field:SerializedName("front_default")
    val frontDefault: String,
)
