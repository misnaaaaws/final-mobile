package com.d121211101.quran.data.repository

import com.d121211101.quran.data.response.GetSurahResponse
import com.d121211101.quran.data.source.remote.ApiService

class SurahRepository (private val apiService: ApiService){
    suspend fun getSurah(): GetSurahResponse {
        return apiService.getSurah()
    }
}