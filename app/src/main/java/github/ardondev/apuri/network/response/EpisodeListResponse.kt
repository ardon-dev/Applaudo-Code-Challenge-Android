package github.ardondev.apuri.network.response

import com.squareup.moshi.Json
import github.ardondev.apuri.network.models.Episode

data class EpisodeListResponse(
    @Json(name = "data")
    val data: List<Episode>?
)
