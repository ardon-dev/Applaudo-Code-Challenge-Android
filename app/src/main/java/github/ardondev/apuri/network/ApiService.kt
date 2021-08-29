package github.ardondev.apuri.network

import github.ardondev.apuri.network.response.AnimeListResponse
import github.ardondev.apuri.network.response.EpisodeListResponse
import retrofit2.http.GET

interface ApiService {

    @GET("anime")
    suspend fun getAnime(): AnimeListResponse

    @GET("episodes")
    suspend fun getEpisodes(): EpisodeListResponse

}