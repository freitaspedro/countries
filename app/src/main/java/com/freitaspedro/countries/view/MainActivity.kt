package com.freitaspedro.countries.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.freitaspedro.countries.R
import com.freitaspedro.countries.viewmodel.ListViewModel
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    lateinit var viewModel: ListViewModel
    private val countriesAdapter = CountryListAdapter(arrayListOf())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewModel = ViewModelProvider(this).get(ListViewModel::class.java)
        viewModel.refresh()

        countriesRecyclerView.apply{
            layoutManager = LinearLayoutManager(context)
            adapter = countriesAdapter
        }

        observeViewModel()
    }

    fun observeViewModel() {
        viewModel.countries.observe(this, { countries ->
            countries?.let {
                countriesRecyclerView.visibility = View.VISIBLE
                countriesAdapter.updateCountries(it)
            }
        })

        viewModel.isError.observe(this, { isError ->
            isError?.let {
                errorTextView.visibility = if (it) View.VISIBLE else View.GONE
            }
        })

        viewModel.isLoading.observe(this, { isLoading ->
            isLoading?.let {
                loadingProgressBar.visibility = if (it) View.VISIBLE else View.GONE
                if (it) {
                    errorTextView.visibility = View.GONE
                    countriesRecyclerView.visibility = View.GONE
                }
            }
        })
    }

}