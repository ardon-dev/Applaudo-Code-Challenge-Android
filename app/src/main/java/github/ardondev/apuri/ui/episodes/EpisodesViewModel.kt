package github.ardondev.apuri.ui.episodes

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import github.ardondev.apuri.network.models.Anime
import github.ardondev.apuri.network.models.Episode
import github.ardondev.apuri.repository.AppRepository
import github.ardondev.apuri.utils.Status
import kotlinx.coroutines.flow.Flow

class EpisodesViewModel(
    private val appRepository: AppRepository,
    private val anime: Anime?
) : ViewModel() {

    //Episodes

    private val _episodeListStatus = MutableLiveData<Status>()
    val episodeListStatus: LiveData<Status>
        get() = _episodeListStatus

    fun setEpisodeStatus(status: Status?) {
        status?.let { _episodeListStatus.postValue(it) }
    }

    private val _episodeListError = MutableLiveData<String>()
    val episodeListError: LiveData<String>
        get() = _episodeListError

    fun setEpisodeError(error: String?) {
        error?.let { _episodeListError.postValue(it) }
    }

    fun getAllAnimeEpisodes(): Flow<PagingData<Episode>> {
        return appRepository.getAllAnimeEpisodes(anime?.id ?: "-1").cachedIn(viewModelScope)
    }

}