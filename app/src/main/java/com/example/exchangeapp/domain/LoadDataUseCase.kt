package com.example.exchangeapp.domain

class LoadDataUseCase(private val repository: Repository) {

     fun loadData() = repository.loadData()

}