package com.ndridm.sportcars

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.ndridm.sportcars.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var rvCars: RecyclerView
    private val list = ArrayList<Cars>()
    private var isLinearLayoutManager = true


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        rvCars = findViewById(R.id.rv_cars)
        rvCars.setHasFixedSize(true)

        list.addAll(getListCars())
        showRecyclerList()
    }

    private fun getListCars(): ArrayList<Cars> {
        val dataName = resources.getStringArray(R.array.data_name)
        val dataDescription = resources.getStringArray(R.array.data_description)
        val dataRating = resources.getStringArray(R.array.data_rating)
        val dataPhoto = resources.getStringArray(R.array.data_photo)
        val dataManufacturer = resources.getStringArray(R.array.data_manufacturer)
        val dataProduction = resources.getStringArray(R.array.data_production)
        val dataEngine = resources.getStringArray(R.array.data_engine)
        val dataAssembly = resources.getStringArray(R.array.data_assembly)
        val dataDesigner = resources.getStringArray(R.array.data_designer)

        val lisCars = ArrayList<Cars>()
        for (i in dataName.indices) {
            val car = Cars(
                dataName[i],
                dataDescription[i],
                dataRating[i],
                dataPhoto[i],
                dataManufacturer[i],
                dataProduction[i],
                dataEngine[i],
                dataAssembly[i],
                dataDesigner[i],
            )
            lisCars.add(car)
        }
        return lisCars
    }

    private fun showRecyclerList() {
        if (isLinearLayoutManager){
            rvCars.layoutManager = LinearLayoutManager(this)
        }else {
            rvCars.layoutManager = GridLayoutManager(this, 2)
        }

        val listCarsAdapter = ListCarsAdapter(list)
        rvCars.adapter = listCarsAdapter

        listCarsAdapter.setOnItemClickCallback(object : ListCarsAdapter.OnItemClickCallback {
            override fun onItemClicked(data: Cars) {
                showSelectedCars(data)
                val intentToDetail = Intent(this@MainActivity, DetailActivity::class.java)
                intentToDetail.putExtra("Data", data)
                startActivity(intentToDetail)
            }
        })
    }

    private fun showSelectedCars(cars: Cars) {
        Toast.makeText(this, "Kamu memilih " + cars.name, Toast.LENGTH_SHORT).show()
    }

    private fun setIcon(menuItem: MenuItem?) {
        if (menuItem == null)
            return

        menuItem.icon =
            if (isLinearLayoutManager)
                ContextCompat.getDrawable(this, R.drawable.baseline_grid_view)
            else ContextCompat.getDrawable(this, R.drawable.baseline_list)

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)

        val layoutButton = menu?.findItem(R.id.switch_icon)
        setIcon(layoutButton)

        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.action_mode -> startActivity(Intent(this@MainActivity, ModeActivity::class.java))
            R.id.action_profile -> startActivity(Intent(this@MainActivity, AboutActivity::class.java))
            R.id.switch_icon -> {
                isLinearLayoutManager = !isLinearLayoutManager
                showRecyclerList()
                setIcon(item)
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }
}