package com.example.pokemon.feature.pokemon

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import br.com.ficha.treino.perloti.fichadetreino.networking.RestDomain
import com.example.pokemon.R
import com.example.pokemon.feature.home.HomeActivity
import com.example.pokemon.model.PokemonResponse
import com.example.pokemon.model.PokemonResult
import kotlinx.android.synthetic.main.activity_home.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class PokemonActivity : AppCompatActivity() {

    var pokemons: List<PokemonResult> = emptyList()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        title = "Pokémon"
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)

        val layoutManagerHor = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        rv.layoutManager = layoutManagerHor

        val domain = RestDomain()

        viewLoading.visibility = View.VISIBLE
        domain.getListPokemon()
            .enqueue(object : Callback<PokemonResponse> {
                override fun onResponse(call: Call<PokemonResponse>, response: Response<PokemonResponse>) {

                    val statusCode = response.code()

                    if (statusCode == 200) {
                        pokemons = response.body()!!.results
                        rv.adapter = PokemonAdapter(this@PokemonActivity,
                            pokemons as ArrayList<PokemonResult>
                        )
                    }
                    viewLoading.visibility = View.GONE
                }

                override fun onFailure(call: Call<PokemonResponse>, t: Throwable) {
                    viewLoading.visibility = View.GONE
                }
            })

    }

    override fun onBackPressed() {
        val intent = Intent(this, HomeActivity::class.java)
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK)
        startActivity(intent)
        finish()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> onBackPressed()
        }
        return super.onOptionsItemSelected(item)
    }
}