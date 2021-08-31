package github.ardondev.apuri.ui.manga

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import github.ardondev.apuri.network.response.CategoryListResponse
import github.ardondev.apuri.network.response.MangaListResponse
import github.ardondev.apuri.repository.AppRepository
import github.ardondev.apuri.utils.Status
import github.ardondev.apuri.utils.getData
import github.ardondev.apuri.utils.getError
import github.ardondev.apuri.utils.succeeded
import kotlinx.coroutines.launch

data class MangaViewModel(
    private val appRepository: AppRepository
): ViewModel() {

    //Categories

    private val _categoryListStatus = MutableLiveData<Status>()
    val categoryListStatus: LiveData<Status>
        get() = _categoryListStatus

    private val _categoryListResponse = MutableLiveData<CategoryListResponse>()
    val categoryListResponse: LiveData<CategoryListResponse>
        get() = _categoryListResponse

    private val _categoryListError = MutableLiveData<String>()
    val categoryListError: LiveData<String>
        get() = _categoryListError

    fun getCategories() {
        viewModelScope.launch {
            _categoryListStatus.postValue(Status.LOADING)
            val result = appRepository.getCategories()

            if (result.succeeded) {
                _categoryListResponse.postValue(result.getData())
                _categoryListStatus.postValue(Status.SUCCESS)

            } else {
                _categoryListError.postValue(result.getError().message)
                _categoryListStatus.postValue(Status.ERROR)

            }

        }

    }


    //Manga

    private val _mangaListStatus = MutableLiveData<Status>()
    val mangaListStatus: LiveData<Status>
        get() = _mangaListStatus

    private val _mangaListResponse = MutableLiveData<MangaListResponse>()
    val mangaListResponse: LiveData<MangaListResponse>
        get() = _mangaListResponse

    private val _mangaListError = MutableLiveData<String>()
    val mangaListError: LiveData<String>
        get() = _mangaListError

    private fun getManga() {
        viewModelScope.launch {
            _mangaListStatus.postValue(Status.LOADING)
            val result = appRepository.getManga()

            if (result.succeeded) {
                _mangaListResponse.postValue(result.getData())
                _mangaListStatus.postValue(Status.SUCCESS)

            } else {
                _mangaListError.postValue(result.getError().message)
                _mangaListStatus.postValue(Status.ERROR)

            }

        }

    }


    //Trending manga

    private val _trendingMangaStatus = MutableLiveData<Status>()
    val trendingMangaStatus: LiveData<Status>
        get() = _trendingMangaStatus

    private val _trendingMangaResponse = MutableLiveData<MangaListResponse>()
    val trendingMangaResponse: LiveData<MangaListResponse>
        get() = _trendingMangaResponse

    private val _trendingMangaError = MutableLiveData<String>()
    val trendingMangaError: LiveData<String>
        get() = _trendingMangaError

    fun getTrendingManga() {
        viewModelScope.launch {
            _trendingMangaStatus.postValue(Status.LOADING)
            val result = appRepository.getTrendingManga()

            if (result.succeeded) {
                _trendingMangaResponse.postValue(result.getData())
                _trendingMangaStatus.postValue(Status.SUCCESS)

            } else {
                _trendingMangaError.postValue(result.getError().message)
                _trendingMangaStatus.postValue(Status.ERROR)

            }

        }

    }


    init {
        getCategories()
        getManga()
        getTrendingManga()
    }

}
