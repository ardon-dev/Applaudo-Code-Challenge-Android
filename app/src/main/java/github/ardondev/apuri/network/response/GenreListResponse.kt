package github.ardondev.apuri.network.response

import com.squareup.moshi.Json
import github.ardondev.apuri.network.models.Genre

data class GenreListResponse(
    @Json(name = "data")
    val data: List<Genre>?
)
