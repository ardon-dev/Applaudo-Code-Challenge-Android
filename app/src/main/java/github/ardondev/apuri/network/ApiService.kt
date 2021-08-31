package github.ardondev.apuri.network

import github.ardondev.apuri.network.response.AnimeListResponse
import github.ardondev.apuri.network.response.CategoryListResponse
import github.ardondev.apuri.network.response.EpisodeListResponse
import github.ardondev.apuri.network.response.GenreListResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.QueryMap

interface ApiService {

    @GET("anime")
    suspend fun getAnime(): AnimeListResponse

    @GET("anime/{id}/episodes")
    suspend fun getAnimeEpisodes(
        @Path("id") id: String
    ): EpisodeListResponse

    @GET("trending/anime")
    suspend fun getAnimeTrending(): AnimeListResponse

    @GET("anime")
    suspend fun getAllAnime(
        @QueryMap limit: Map<String, Int>?,
        @QueryMap offset: Map<String, Int>?,
        @QueryMap search: Map<String, String>?,
        @QueryMap category: Map<String, String>?
    ): AnimeListResponse

    @GET("categories")
    suspend fun getCategories(): CategoryListResponse

    @GET("categories")
    suspend fun getAllCategories(
        @QueryMap limit: Map<String, Int>?,
        @QueryMap offset: Map<String, Int>?,
        @QueryMap search: Map<String, String>?
    ): CategoryListResponse

    @GET("anime/{id}/genres")
    suspend fun getAnimeGenres(
        @Path("id") id: String
    ): GenreListResponse

}