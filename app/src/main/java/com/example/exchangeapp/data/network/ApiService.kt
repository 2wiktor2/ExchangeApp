package com.example.exchangeapp.data.network

import com.example.exchangeapp.data.network.model.RawResponseDto
import io.reactivex.Single
import retrofit2.http.GET

interface ApiService {

    //    https://www.cbr-xml-daily.ru/daily_json.js
    @GET("daily_json.js")
    fun getCurrency(): Single<RawResponseDto>


}