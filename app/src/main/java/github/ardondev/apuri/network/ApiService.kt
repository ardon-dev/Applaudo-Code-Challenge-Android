package github.ardondev.apuri.network

import github.ardondev.apuri.network.response.AnimeListResponse
import github.ardondev.apuri.network.response.EpisodeListResponse
import retrofit2.http.GET
import retrofit2.http.QueryMap

interface ApiService {

    @GET("anime")
    suspend fun getAnime(
        @QueryMap limit: Map<String, Int>? = null,
        @QueryMap offset: Map<String, Int>? = null
    ): AnimeListResponse

    @GET("episodes")
    suspend fun getEpisodes(): EpisodeListResponse

    @GET("trending/anime")
    suspend fun getAnimeTrending(): AnimeListResponse

}