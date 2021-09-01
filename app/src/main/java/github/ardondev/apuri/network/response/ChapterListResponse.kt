package github.ardondev.apuri.network.response

import com.squareup.moshi.Json
import github.ardondev.apuri.network.models.Chapter

data class ChapterListResponse(
    @Json(name = "data")
    val data: List<Chapter>?
)
