package github.ardondev.apuri.ui.anime_all

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import github.ardondev.apuri.network.models.Anime
import github.ardondev.apuri.network.models.Category
import github.ardondev.apuri.repository.AppRepository
import github.ardondev.apuri.utils.Status
import kotlinx.coroutines.flow.Flow

class AnimeAllViewModel(
    private val appRepository: AppRepository,
    private val category: String?
): ViewModel() {

    //Search query

    val searchQuery = MutableLiveData<String?>()


    //Anime

    private val _animeListStatus = MutableLiveData<Status>()
    val animeListStatus: LiveData<Status>
        get() = _animeListStatus
    fun setAnimeListStatus(status: Status?) {
        status?.let { _animeListStatus.postValue(it) }
    }

    private val _animeListError = MutableLiveData<String>()
    val animeListError: LiveData<String>
        get() = _animeListError
    fun setAnimeListError(error: String?) {
        error?.let { _animeListError.postValue(it) }
    }

    fun getAllAnime(): Flow<PagingData<Anime>> {
        return appRepository.getAllAnime(searchQuery.value, category).cachedIn(viewModelScope)
    }


    init {
        searchQuery.postValue(null)
    }

}