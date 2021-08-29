package github.ardondev.apuri.ui.anime

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import github.ardondev.apuri.network.response.AnimeListResponse
import github.ardondev.apuri.network.response.EpisodeListResponse
import github.ardondev.apuri.repository.AppRepository
import github.ardondev.apuri.utils.Status
import github.ardondev.apuri.utils.getData
import github.ardondev.apuri.utils.getError
import github.ardondev.apuri.utils.succeeded
import kotlinx.coroutines.launch

class AnimeViewModel(
    private val appRepository: AppRepository
) : ViewModel() {

    //Anime

    private val _animeListStatus = MutableLiveData<Status>()
    val animeListStatus: LiveData<Status>
        get() = _animeListStatus

    private val _animeListResponse = MutableLiveData<AnimeListResponse>()
    val animeListResponse: LiveData<AnimeListResponse>
        get() = _animeListResponse

    private val _animeListError = MutableLiveData<String>()
    val animeListError: LiveData<String>
        get() = _animeListError

    fun getAnime() {
        viewModelScope.launch {
            _animeListStatus.postValue(Status.LOADING)
            val result = appRepository.getAnime()

            if (result.succeeded) {
                _animeListResponse.postValue(result.getData())
                _animeListStatus.postValue(Status.SUCCESS)

            } else {
                _animeListError.postValue(result.getError().message)
                _animeListStatus.postValue(Status.ERROR)

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
            val result = appRepository.getEpisodes()

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
        getAnime()
        getEpisodes()
    }

}