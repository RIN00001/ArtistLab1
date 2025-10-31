package com.example.artistlab.ui.model

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.artistlab.db.repository.ArtistRepo
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

sealed class ArtistUIState {
    object Loading : ArtistUIState()
    data class Success(val artist: Artist) : ArtistUIState()
    data class Error(val message: String) : ArtistUIState()
}

class ArtistViewModel : ViewModel() {
    private val repository = ArtistRepo()

    private val _artistState = MutableStateFlow<ArtistUIState>(ArtistUIState.Loading)
    val artistState: StateFlow<ArtistUIState> = _artistState.asStateFlow()

    init {
        fetchArtistData()
    }

    private fun fetchArtistData() {
        viewModelScope.launch {
            _artistState.value = ArtistUIState.Loading
            try {
                val artist = repository.getArtistData()
                if (artist != null) {
                    _artistState.value = ArtistUIState.Success(artist)
                } else {
                    _artistState.value = ArtistUIState.Error("Artist data not found")
                }
            } catch (e: Exception) {
                val message = when {
                    e.message?.contains("Unable to resolve host") == true ->
                        "Network error: Check internet"
                    e.message?.contains("timeout") == true ->
                        "Request timeout"
                    else ->
                        "Error fetching artist data: ${e.message ?: "Unknown"}"
                }
                _artistState.value = ArtistUIState.Error(message)
            }
        }
    }

    fun retryFetch() {
        fetchArtistData()
    }
}
