package github.ardondev.apuri.network.response

import com.squareup.moshi.Json
import github.ardondev.apuri.network.models.Anime

data class AnimeListResponse(
    @Json(name = "data")
    val data: List<Anime>?
)
