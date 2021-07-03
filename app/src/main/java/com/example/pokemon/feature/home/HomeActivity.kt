package com.example.pokemon.feature.home

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.pokemon.R
import com.example.pokemon.feature.about.AboutActivity
import com.example.pokemon.feature.pokemon.PokemonActivity
import kotlinx.android.synthetic.main.activity_main.*

class HomeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        buttonPoke.setOnClickListener {
            startActivity(Intent(this@HomeActivity, PokemonActivity::class.java))
            finish()
        }

        buttonAbout.setOnClickListener {
            startActivity(Intent(this@HomeActivity, AboutActivity::class.java))
            finish()
        }

    }
}