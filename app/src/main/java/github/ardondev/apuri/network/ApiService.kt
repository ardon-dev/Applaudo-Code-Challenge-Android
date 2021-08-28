package github.ardondev.apuri.network

import github.ardondev.apuri.network.response.AnimeListResponse
import retrofit2.http.GET

interface ApiService {

    @GET("anime")
    suspend fun getAnime(): AnimeListResponse

}