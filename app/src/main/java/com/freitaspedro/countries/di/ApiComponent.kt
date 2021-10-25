package com.freitaspedro.countries.di

import com.freitaspedro.countries.model.CountriesService
import com.freitaspedro.countries.viewmodel.ListViewModel
import dagger.Component

@Component(modules = [ApiModule::class])
interface ApiComponent {

    fun inject(service: CountriesService)

    fun inject(viewModel: ListViewModel)

}