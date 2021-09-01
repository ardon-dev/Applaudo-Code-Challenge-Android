package github.ardondev.apuri.repository

import androidx.paging.PagingData
import github.ardondev.apuri.network.models.Anime
import github.ardondev.apuri.network.models.Category
import github.ardondev.apuri.network.response.*
import github.ardondev.apuri.utils.Result
import kotlinx.coroutines.flow.Flow

interface AppRepository {

    suspend fun getAnime(): Result<AnimeListResponse>

    suspend fun getAnimeTrending(): Result<AnimeListResponse>

    fun getAllAnime(search: String?, category: String?): Flow<PagingData<Anime>>

    suspend fun getCategories(): Result<CategoryListResponse>

    fun getAllCategories(search: String?): Flow<PagingData<Category>>

    suspend fun getAnimeGenres(id: String): Result<GenreListResponse>

    suspend fun getAnimeEpisodes(id: String): Result<EpisodeListResponse>

    suspend fun getManga(): Result<MangaListResponse>

    suspend fun getTrendingManga(): Result<MangaListResponse>

    suspend fun getMangaGenres(id: String): Result<GenreListResponse>

    suspend fun getMangaChapters(id: String): Result<ChapterListResponse>

}