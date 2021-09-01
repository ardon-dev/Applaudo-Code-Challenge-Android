package github.ardondev.apuri.ui.manga_detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import github.ardondev.apuri.network.models.Manga
import github.ardondev.apuri.network.response.ChapterListResponse
import github.ardondev.apuri.network.response.GenreListResponse
import github.ardondev.apuri.repository.AppRepository
import github.ardondev.apuri.utils.Status
import github.ardondev.apuri.utils.getData
import github.ardondev.apuri.utils.getError
import github.ardondev.apuri.utils.succeeded
import kotlinx.coroutines.launch

class MangaDetailViewModel(
    private val appRepository: AppRepository,
    private val manga: Manga?
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

    fun getMangaGenres() {
        viewModelScope.launch {
            _genreListStatus.postValue(Status.LOADING)
            val result = appRepository.getMangaGenres(manga?.id ?: "-1")

            if (result.succeeded) {
                _genreListResponse.postValue(result.getData())
                _genreListStatus.postValue(Status.SUCCESS)

            } else {
                _genreListError.postValue(result.getError().message)
                _genreListStatus.postValue(Status.ERROR)

            }

        }

    }


    //Chapters

    private val _chapterListStatus = MutableLiveData<Status>()
    val chapterListStatus: LiveData<Status>
        get() = _chapterListStatus

    private val _chapterListResponse = MutableLiveData<ChapterListResponse>()
    val chapterListResponse: LiveData<ChapterListResponse>
        get() = _chapterListResponse

    private val _chapterListError = MutableLiveData<String>()
    val chapterListError: LiveData<String>
        get() = _chapterListError

    fun getMangaChapters() {
        viewModelScope.launch {
            _chapterListStatus.postValue(Status.LOADING)
            val result = appRepository.getMangaChapters(manga?.id ?: "-1")

            if (result.succeeded) {
                _chapterListResponse.postValue(result.getData())
                _chapterListStatus.postValue(Status.SUCCESS)

            } else {
                _chapterListError.postValue(result.getError().message)
                _chapterListStatus.postValue(Status.ERROR)

            }

        }

    }

    init {
        getMangaGenres()
        getMangaChapters()
    }

}