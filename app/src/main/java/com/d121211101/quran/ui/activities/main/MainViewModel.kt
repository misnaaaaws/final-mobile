package com.d121211101.quran.ui.activities.main

import android.app.Application
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.d121211101.quran.data.models.Data
import com.d121211101.quran.data.repository.SurahRepository
import com.d121211101.quran.ui.MyApplication
import kotlinx.coroutines.launch
import java.io.IOException

sealed interface MainUiState {
    data class Success(val surahs: List<Data>) : MainUiState
    object Error : MainUiState
    object Loading : MainUiState
}

class MainViewModel(application: Application, private val surahRepository: SurahRepository) :
    AndroidViewModel(application) {

    var mainUiState: MainUiState by mutableStateOf(MainUiState.Loading)
        private set

    fun getSurah() = viewModelScope.launch {
        mainUiState = MainUiState.Loading
        try {
            val result = surahRepository.getSurah()
            mainUiState = MainUiState.Success(result.data.orEmpty())
        } catch (e: IOException) {
            mainUiState = MainUiState.Error
        }
    }

    init {
        getSurah()
    }

    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val application = (this[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY] as Application)
                val surahRepository = (application as MyApplication).container.surahRepository
                MainViewModel(application, surahRepository)
            }
        }
    }
}
