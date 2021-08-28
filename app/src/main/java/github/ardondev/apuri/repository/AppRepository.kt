package github.ardondev.apuri.repository

import github.ardondev.apuri.network.response.AnimeListResponse
import github.ardondev.apuri.utils.Result

interface AppRepository {

    suspend fun getAnime(): Result<AnimeListResponse>

}