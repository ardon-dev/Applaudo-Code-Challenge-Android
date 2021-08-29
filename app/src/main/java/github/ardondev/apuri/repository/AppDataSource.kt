package github.ardondev.apuri.repository

import github.ardondev.apuri.network.ApiService
import github.ardondev.apuri.network.response.AnimeListResponse
import github.ardondev.apuri.network.response.EpisodeListResponse
import github.ardondev.apuri.utils.Result

class AppDataSource(
    private val apiService: ApiService
): AppRepository {

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

}