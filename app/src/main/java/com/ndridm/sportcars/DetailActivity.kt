package com.ndridm.sportcars

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.bumptech.glide.Glide
import com.ndridm.sportcars.databinding.ActivityDetailBinding

class DetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.hide()
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        enableEdgeToEdge()
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        binding.btnBack.setOnClickListener {
            val intentToMain = Intent(this@DetailActivity, MainActivity::class.java)
            startActivity(intentToMain)
        }


        val data = intent.getParcelableExtra<Cars>("Data")
        data?.photo?.let { photo ->
            Glide.with(this)
                .load(photo) // URL Gambar
                .into(binding.imageView)
        }
        binding.tvName.text = data?.name
        binding.tvRating.text = data?.rating
        binding.tvDescription.text = data?.description
        binding.tvManufacturer.text = data?.manufacturer
        binding.tvProduction.text = data?.production
        binding.tvEngine.text = data?.engine
        binding.tvAssembly.text = data?.assembly
        binding.tvDesigner.text = data?.designer
    }

}