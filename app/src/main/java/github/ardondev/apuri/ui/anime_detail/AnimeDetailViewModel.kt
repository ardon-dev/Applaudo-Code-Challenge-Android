package github.ardondev.apuri.ui.anime_detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import github.ardondev.apuri.network.response.EpisodeListResponse
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

    fun getAnimeGenres() {
        viewModelScope.launch {
            _genreListStatus.postValue(Status.LOADING)
            val result = appRepository.getAnimeGenres(id ?: "")

            if (result.succeeded) {
                _genreListResponse.postValue(result.getData())
                _genreListStatus.postValue(Status.SUCCESS)

            } else {
                _genreListError.postValue(result.getError().message)
                _genreListStatus.postValue(Status.ERROR)

            }

        }

    }


    //Episodes

    private val _episodeListStatus = MutableLiveData<Status>()
    val episodeListStatus: LiveData<Status>
        get() = _episodeListStatus

    private val _episodeListResponse = MutableLiveData<EpisodeListResponse>()
    val episodeListResponse: LiveData<EpisodeListResponse>
        get() = _episodeListResponse

    private val _episodeListError = MutableLiveData<String>()
    val episodeListError: LiveData<String>
        get() = _episodeListError

    fun getEpisodes() {
        viewModelScope.launch {
            _episodeListStatus.postValue(Status.LOADING)
            val result = appRepository.getAnimeEpisodes(id ?: "")

            if (result.succeeded) {
                _episodeListResponse.postValue(result.getData())
                _episodeListStatus.postValue(Status.SUCCESS)

            } else {
                _episodeListError.postValue(result.getError().message)
                _episodeListStatus.postValue(Status.ERROR)

            }

        }

    }


    init {
        getAnimeGenres()
        getEpisodes()
    }

}