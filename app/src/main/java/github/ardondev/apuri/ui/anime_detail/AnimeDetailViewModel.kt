package github.ardondev.apuri.ui.anime_detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import github.ardondev.apuri.network.response.GenreListResponse
import github.ardondev.apuri.repository.AppRepository
import github.ardondev.apuri.utils.Status
import github.ardondev.apuri.utils.getData
import github.ardondev.apuri.utils.getError
import github.ardondev.apuri.utils.succeeded
import kotlinx.coroutines.launch

class AnimeDetailViewModel(
    private val appRepository: AppRepository,
    private val id: String?
): ViewModel() {

    //Genres

    private val _genreListStatus = MutableLiveData<Status>()
    val genreListStatus: LiveData<Status>
        get() = _genreListStatus

    private val _genreListResponse = MutableLiveData<GenreListResponse>()
    val genreListResponse: LiveData<GenreListResponse>
        get() = _genreListResponse

    private val _genreListError = MutableLiveData<String>()
    val genreListError: LiveData<String>
        get() = _genreListError

    fun getGenres() {
        viewModelScope.launch {
            _genreListStatus.postValue(Status.LOADING)
            val result = appRepository.getGenres(id ?: "")

            if (result.succeeded) {
                _genreListResponse.postValue(result.getData())
                _genreListStatus.postValue(Status.SUCCESS)

            } else {
                _genreListError.postValue(result.getError().message)
                _genreListStatus.postValue(Status.ERROR)

            }

        }

    }


    init {
        getGenres()
    }

}