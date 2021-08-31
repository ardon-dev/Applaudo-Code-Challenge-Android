package github.ardondev.apuri.network.models

import com.squareup.moshi.JsonClass
import com.squareup.moshi.Json

@JsonClass(generateAdapter = true)
data class Genre(
    @Json(name = "attributes")
    val attributes: Attributes?,
    @Json(name = "id")
    val id: String?,
    @Json(name = "type")
    val type: String?
) {

    @JsonClass(generateAdapter = true)
    data class Attributes(
        @Json(name = "createdAt")
        val createdAt: String?,
        @Json(name = "description")
        val description: Any?,
        @Json(name = "name")
        val name: String?,
        @Json(name = "slug")
        val slug: String?,
        @Json(name = "updatedAt")
        val updatedAt: String?
    )

}