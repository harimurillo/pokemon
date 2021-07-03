package com.example.pokemon.repository

import com.example.pokemon.model.PokemonResponse
import retrofit2.Call
import retrofit2.http.GET


interface RestAPI {
    @GET("v2/pokemon?limit=150")
    fun getListPokemon(): Call<PokemonResponse>





}