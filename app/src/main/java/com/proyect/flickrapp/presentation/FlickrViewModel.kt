package com.proyect.flickrapp.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.proyect.flickrapp.data.remote.FlickrApi
import com.proyect.flickrapp.data.remote.FlickrListDto
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FlickrViewModel @Inject constructor(
    private val flickrApi: FlickrApi
): ViewModel() {

    private val _flickrs = MutableLiveData<FlickrListDto>()
    val flickrs: LiveData<FlickrListDto>
        get() = _flickrs

    init {
        viewModelScope.launch {
            try {
                _flickrs.value = flickrApi.getBeers(
                    page = 1,
                    perPage = 9
                )
            } catch (e: Exception) {
                // Maneja la excepción según tus necesidades (puedes emitir un estado de error, etc.).
            }
        }

    }


}