package github.ardondev.apuri.network.models
import com.squareup.moshi.JsonClass

import com.squareup.moshi.Json

@JsonClass(generateAdapter = true)
data class Episode(
    @Json(name = "attributes")
    val attributes: Attributes?,
    @Json(name = "id")
    val id: String?,
    @Json(name = "type")
    val type: String?
) {
    @JsonClass(generateAdapter = true)
    data class Attributes(
        @Json(name = "airdate")
        val airdate: String?,
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
        @Json(name = "relativeNumber")
        val relativeNumber: Int?,
        @Json(name = "seasonNumber")
        val seasonNumber: Int?,
        @Json(name = "synopsis")
        val synopsis: String?,
        @Json(name = "thumbnail")
        val thumbnail: Thumbnail?,
        @Json(name = "titles")
        val titles: Titles?,
        @Json(name = "updatedAt")
        val updatedAt: String?
    ) {
        @JsonClass(generateAdapter = true)
        data class Thumbnail(
            @Json(name = "original")
            val original: String?
        )

        @JsonClass(generateAdapter = true)
        data class Titles(
            @Json(name = "en_jp")
            val enJp: String?,
            @Json(name = "en_us")
            val enUs: String?,
            @Json(name = "ja_jp")
            val jaJp: String?
        )

    }

}