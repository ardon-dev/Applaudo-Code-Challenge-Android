package github.ardondev.apuri.network.models

import com.squareup.moshi.JsonClass

import com.squareup.moshi.Json

@JsonClass(generateAdapter = true)
data class Category(
    @Json(name = "attributes")
    val attributes: Attributes?,
    @Json(name = "id")
    val id: String?,
    @Json(name = "type")
    val type: String?
) {
    @JsonClass(generateAdapter = true)
    data class Attributes(
        @Json(name = "childCount")
        val childCount: Int?,
        @Json(name = "createdAt")
        val createdAt: String?,
        @Json(name = "description")
        val description: String?,
        @Json(name = "image")
        val image: Image?,
        @Json(name = "nsfw")
        val nsfw: Boolean?,
        @Json(name = "slug")
        val slug: String?,
        @Json(name = "title")
        val title: String?,
        @Json(name = "totalMediaCount")
        val totalMediaCount: Int?,
        @Json(name = "updatedAt")
        val updatedAt: String?
    ) {

        @JsonClass(generateAdapter = true)
        data class Image(
            @Json(name = "large")
            val large: String?,
            @Json(name = "medium")
            val medium: String?,
            @Json(name = "original")
            val original: String?,
            @Json(name = "small")
            val small: String?,
            @Json(name = "tiny")
            val tiny: String?
        )

    }

}