package com.example.exchangeapp.data.mapper

import com.example.exchangeapp.data.database.CurrencyDbModel
import com.example.exchangeapp.data.network.model.CurrencyDto
import com.example.exchangeapp.data.network.model.RawResponseDto
import com.example.exchangeapp.domain.CurrencyEntity
import com.google.gson.Gson

class CurrencyMapper {

    fun mapDtoToDbModel(dto: CurrencyDto): CurrencyDbModel {
        return CurrencyDbModel(
            name = dto.name,
            charCode = dto.charCode,
            nominal = dto.nominal,
            value = dto.value,
            previous = dto.previous
        )
    }

    fun mapResponseToCurrencyList(response: RawResponseDto): List<CurrencyDto> {
        val result = mutableListOf<CurrencyDto>()

        val jsonObject = response.valute ?: return result

        val coinKeySet = jsonObject.keySet()
        for (coinKey in coinKeySet) {
            val currency= Gson().fromJson(
                jsonObject.getAsJsonObject(coinKey), CurrencyDto::class.java
            )
            result.add(currency)
        }
        return result
    }

    fun mapDbModelToEntity(dbModel: CurrencyDbModel): CurrencyEntity {
        return CurrencyEntity(
            name = dbModel.name,
            charCode = dbModel.charCode,
            nominal = dbModel.nominal,
            value = dbModel.value,
            previous = dbModel.previous
        )
    }

}