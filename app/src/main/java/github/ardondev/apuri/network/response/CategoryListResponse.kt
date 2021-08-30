package github.ardondev.apuri.network.response

import com.squareup.moshi.Json
import github.ardondev.apuri.network.models.Category

data class CategoryListResponse(
    @Json(name = "data")
    val data: List<Category>?
)
