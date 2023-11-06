package com.example.myapplication


import androidx.lifecycle.ViewModel

class CountryViewModel : ViewModel() {
    private var countryList: List<Country> = mutableListOf() // Initialize with your data

    fun setCountryList(countryList: List<Country>) {
        this.countryList = countryList
    }

    fun getCountryList(): List<Country> {
        return countryList
    }
}