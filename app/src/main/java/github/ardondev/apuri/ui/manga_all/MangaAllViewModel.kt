package github.ardondev.apuri.ui.manga_all

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import github.ardondev.apuri.network.models.Manga
import github.ardondev.apuri.repository.AppRepository
import github.ardondev.apuri.utils.Status
import kotlinx.coroutines.flow.Flow

class MangaAllViewModel(
    private val appRepository: AppRepository,
    private val category: String?
) : ViewModel() {

    //Search query

    val searchQuery = MutableLiveData<String?>()


    //Manga

    private val _mangaListStatus = MutableLiveData<Status>()
    val mangaListStatus: LiveData<Status>
        get() = _mangaListStatus
    fun setMangaListStatus(status: Status?) {
        status?.let { _mangaListStatus.postValue(it) }
    }

    private val _mangaListError = MutableLiveData<String>()
    val mangaListError: LiveData<String>
        get() = _mangaListError
    fun setMangaListError(error: String?) {
        error?.let { _mangaListError.postValue(it) }
    }

    fun getAllManga(): Flow<PagingData<Manga>> {
        return appRepository.getAllManga(searchQuery.value, category).cachedIn(viewModelScope)
    }


    init {
        searchQuery.postValue(null)
    }

}