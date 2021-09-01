package github.ardondev.apuri.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import github.ardondev.apuri.network.ApiService
import github.ardondev.apuri.network.models.Anime
import github.ardondev.apuri.network.models.Category
import github.ardondev.apuri.network.models.Episode
import github.ardondev.apuri.network.models.Manga
import github.ardondev.apuri.network.response.*
import github.ardondev.apuri.repository.paging.AnimePagingDataSource
import github.ardondev.apuri.repository.paging.CategoryPagingDataSource
import github.ardondev.apuri.repository.paging.EpisodePagingDataSource
import github.ardondev.apuri.repository.paging.MangaPagingDataSource
import github.ardondev.apuri.utils.Result
import kotlinx.coroutines.flow.Flow

class AppDataSource(
    private val apiService: ApiService
) : AppRepository {

    override suspend fun getAnime(): Result<AnimeListResponse> {
        return try {
            Result.Success(apiService.getAnime())
        } catch (e: Exception) {
            Result.Error(e)
        }
    }

    override suspend fun getAnimeTrending(): Result<AnimeListResponse> {
        return try {
            Result.Success(apiService.getAnimeTrending())
        } catch (e: Exception) {
            Result.Error(e)
        }
    }

    override fun getAllAnime(search: String?, category: String?): Flow<PagingData<Anime>> {
        return Pager(
            config = PagingConfig(pageSize = 10),
            pagingSourceFactory = {
                AnimePagingDataSource(
                    apiService = apiService,
                    search = search,
                    category = category
                )
            }
        ).flow
    }

    override suspend fun getCategories(): Result<CategoryListResponse> {
        return try {
            Result.Success(apiService.getCategories())
        } catch (e: Exception) {
            Result.Error(e)
        }
    }

    override fun getAllCategories(search: String?): Flow<PagingData<Category>> {
        return Pager(
            config = PagingConfig(pageSize = 10),
            pagingSourceFactory = {
                CategoryPagingDataSource(
                    apiService = apiService,
                    search = search
                )
            }
        ).flow
    }

    override suspend fun getAnimeGenres(id: String): Result<GenreListResponse> {
        return try {
            Result.Success(apiService.getAnimeGenres(id))
        } catch (e: Exception) {
            Result.Error(e)
        }
    }

    override suspend fun getAnimeEpisodes(id: String): Result<EpisodeListResponse> {
        return try {
            Result.Success(apiService.getAnimeEpisodes(id))
        } catch (e: Exception) {
            Result.Error(e)
        }
    }

    override suspend fun getManga(): Result<MangaListResponse> {
        return try {
            Result.Success(apiService.getManga())
        } catch (e: Exception) {
            Result.Error(e)
        }
    }

    override suspend fun getTrendingManga(): Result<MangaListResponse> {
        return try {
            Result.Success(apiService.getTrendingManga())
        } catch (e: Exception) {
            Result.Error(e)
        }
    }

    override suspend fun getMangaGenres(id: String): Result<GenreListResponse> {
        return try {
            Result.Success(apiService.getMangaGenres(id))
        } catch (e: Exception) {
            Result.Error(e)
        }
    }

    override suspend fun getMangaChapters(id: String): Result<ChapterListResponse> {
        return try {
            Result.Success(apiService.getMangaChapters(id))
        } catch (e: Exception) {
            Result.Error(e)
        }
    }

    override fun getAllManga(search: String?, category: String?): Flow<PagingData<Manga>> {
        return Pager(
            config = PagingConfig(pageSize = 10),
            pagingSourceFactory = {
                MangaPagingDataSource(
                    apiService = apiService,
                    search = search,
                    category = category
                )
            }
        ).flow
    }

    override fun getAllAnimeEpisodes(animeId: String): Flow<PagingData<Episode>> {
        return Pager(
            config = PagingConfig(pageSize = 10),
            pagingSourceFactory = {
                EpisodePagingDataSource(
                    apiService = apiService,
                    animeId = animeId
                )
            }
        ).flow
    }

}