package github.ardondev.apuri.ui.manga

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import github.ardondev.apuri.network.response.CategoryListResponse
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


    init {
        getCategories()
    }

}
