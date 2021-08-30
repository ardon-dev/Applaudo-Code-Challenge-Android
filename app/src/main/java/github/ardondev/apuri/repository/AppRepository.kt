package github.ardondev.apuri.repository

import androidx.paging.PagingData
import github.ardondev.apuri.network.models.Anime
import github.ardondev.apuri.network.models.Category
import github.ardondev.apuri.network.response.AnimeListResponse
import github.ardondev.apuri.network.response.CategoryListResponse
import github.ardondev.apuri.network.response.EpisodeListResponse
import github.ardondev.apuri.utils.Result
import kotlinx.coroutines.flow.Flow

interface AppRepository {

    suspend fun getAnime(): Result<AnimeListResponse>

    suspend fun getEpisodes(): Result<EpisodeListResponse>

    suspend fun getAnimeTrending(): Result<AnimeListResponse>

    fun getAllAnime(search: String?, category: String?): Flow<PagingData<Anime>>

    suspend fun getCategories(): Result<CategoryListResponse>

    fun getAllCategories(search: String?): Flow<PagingData<Category>>

}