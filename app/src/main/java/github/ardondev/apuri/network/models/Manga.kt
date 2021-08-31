package github.ardondev.apuri.network.models

import android.os.Parcelable
import com.squareup.moshi.JsonClass
import com.squareup.moshi.Json
import kotlinx.parcelize.Parcelize

@Parcelize
@JsonClass(generateAdapter = true)
data class Manga(
    @Json(name = "attributes")
    val attributes: Attributes?,
    @Json(name = "id")
    val id: String?,
    @Json(name = "type")
    val type: String?
): Parcelable {

    @Parcelize
    @JsonClass(generateAdapter = true)
    data class Attributes(
        @Json(name = "abbreviatedTitles")
        val abbreviatedTitles: List<String?>?,
        @Json(name = "ageRating")
        val ageRating: String?,
        @Json(name = "ageRatingGuide")
        val ageRatingGuide: String?,
        @Json(name = "averageRating")
        val averageRating: String?,
        @Json(name = "canonicalTitle")
        val canonicalTitle: String?,
        @Json(name = "chapterCount")
        val chapterCount: Int?,
        @Json(name = "coverImage")
        val coverImage: CoverImage?,
        @Json(name = "coverImageTopOffset")
        val coverImageTopOffset: Int?,
        @Json(name = "createdAt")
        val createdAt: String?,
        @Json(name = "description")
        val description: String?,
        @Json(name = "endDate")
        val endDate: String?,
        @Json(name = "favoritesCount")
        val favoritesCount: Int?,
        @Json(name = "mangaType")
        val mangaType: String?,
        @Json(name = "popularityRank")
        val popularityRank: Int?,
        @Json(name = "posterImage")
        val posterImage: PosterImage?,
        @Json(name = "ratingRank")
        val ratingRank: Int?,
        @Json(name = "serialization")
        val serialization: String?,
        @Json(name = "slug")
        val slug: String?,
        @Json(name = "startDate")
        val startDate: String?,
        @Json(name = "status")
        val status: String?,
        @Json(name = "subtype")
        val subtype: String?,
        @Json(name = "synopsis")
        val synopsis: String?,
        @Json(name = "updatedAt")
        val updatedAt: String?,
        @Json(name = "userCount")
        val userCount: Int?,
        @Json(name = "volumeCount")
        val volumeCount: Int?,
    ): Parcelable {

        @Parcelize
        @JsonClass(generateAdapter = true)
        data class CoverImage(
            @Json(name = "large")
            val large: String?,
            @Json(name = "original")
            val original: String?,
            @Json(name = "small")
            val small: String?,
            @Json(name = "tiny")
            val tiny: String?
        ): Parcelable

        @Parcelize
        @JsonClass(generateAdapter = true)
        data class PosterImage(
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
        ): Parcelable

    }

}