package github.ardondev.apuri.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import github.ardondev.apuri.network.ApiService
import github.ardondev.apuri.network.models.Anime
import github.ardondev.apuri.network.response.AnimeListResponse
import github.ardondev.apuri.network.response.EpisodeListResponse
import github.ardondev.apuri.repository.paging.AnimePagingDataSource
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

    override suspend fun getEpisodes(): Result<EpisodeListResponse> {
        return try {
            Result.Success(apiService.getEpisodes())
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

    override fun getAllAnime(search: String?): Flow<PagingData<Anime>> {
        return Pager(
            config = PagingConfig(pageSize = 10),
            pagingSourceFactory = {
                AnimePagingDataSource(
                    apiService = apiService,
                    search = search
                )
            }
        ).flow
    }

}