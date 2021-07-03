package br.com.ficha.treino.perloti.fichadetreino.networking

import com.example.pokemon.App
import com.example.pokemon.repository.RestAPI
import com.example.pokemon.model.PokemonResponse
import retrofit2.Call

class RestDomain {
    private val service: RestAPI

    init {
        service = App.getRestClient().createService(RestAPI::class.java)
    }

    fun getListPokemon(): Call<PokemonResponse> {
        return service.getListPokemon()
    }



}