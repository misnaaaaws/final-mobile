package com.d121211101.quran.data.source.remote

import com.d121211101.quran.data.response.GetSurahResponse
import retrofit2.http.GET

interface ApiService {
    @GET("v1/surah")
    suspend fun getSurah(): GetSurahResponse
}