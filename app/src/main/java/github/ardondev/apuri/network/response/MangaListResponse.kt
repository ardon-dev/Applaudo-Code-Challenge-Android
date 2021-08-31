package github.ardondev.apuri.network.response

import com.squareup.moshi.Json
import github.ardondev.apuri.network.models.Manga

data class MangaListResponse(
    @Json(name = "data")
    val data: List<Manga>?
)
