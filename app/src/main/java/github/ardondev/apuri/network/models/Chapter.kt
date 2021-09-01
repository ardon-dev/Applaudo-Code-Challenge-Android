package github.ardondev.apuri.network.models
import com.squareup.moshi.JsonClass

import com.squareup.moshi.Json

@JsonClass(generateAdapter = true)
data class Chapter(
    @Json(name = "attributes")
    val attributes: Attributes?,
    @Json(name = "id")
    val id: String?,
    @Json(name = "type")
    val type: String?
) {

    @JsonClass(generateAdapter = true)
    data class Attributes(
        @Json(name = "canonicalTitle")
        val canonicalTitle: String?,
        @Json(name = "createdAt")
        val createdAt: String?,
        @Json(name = "description")
        val description: String?,
        @Json(name = "length")
        val length: Int?,
        @Json(name = "number")
        val number: Int?,
        @Json(name = "published")
        val published: String?,
        @Json(name = "synopsis")
        val synopsis: String?,
        @Json(name = "thumbnail")
        val thumbnail: String?,
        @Json(name = "updatedAt")
        val updatedAt: String?,
        @Json(name = "volumeNumber")
        val volumeNumber: Int?
    )

}