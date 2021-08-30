package github.ardondev.apuri.ui.categories

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import github.ardondev.apuri.network.models.Category
import github.ardondev.apuri.repository.AppRepository
import github.ardondev.apuri.utils.Status
import kotlinx.coroutines.flow.Flow

class CategoriesViewModel(
    private val appRepository: AppRepository
): ViewModel() {

    //Categories

    private val _categoryListStatus = MutableLiveData<Status>()
    val categoryListStatus: LiveData<Status>
        get() = _categoryListStatus
    fun setCategoryListStatus(status: Status?) {
        status?.let { _categoryListStatus.postValue(it) }
    }

    private val _categoryListError = MutableLiveData<String>()
    val categoryListError: LiveData<String>
        get() = _categoryListError
    fun setCategoryListError(error: String?) {
        error?.let { _categoryListError.postValue(it) }
    }

    fun getAllCategories(): Flow<PagingData<Category>> {
        return appRepository.getAllCategories(null).cachedIn(viewModelScope)
    }

}