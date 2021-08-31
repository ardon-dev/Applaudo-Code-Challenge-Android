package github.ardondev.apuri.network.models

import android.os.Parcelable
import com.squareup.moshi.JsonClass
import com.squareup.moshi.Json
import kotlinx.parcelize.Parcelize

@Parcelize
@JsonClass(generateAdapter = true)
data class Anime(
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
        @Json(name = "episodeCount")
        val episodeCount: Int?,
        @Json(name = "episodeLength")
        val episodeLength: Int?,
        @Json(name = "favoritesCount")
        val favoritesCount: Int?,
        @Json(name = "nsfw")
        val nsfw: Boolean?,
        @Json(name = "popularityRank")
        val popularityRank: Int?,
        @Json(name = "posterImage")
        val posterImage: PosterImage?,
        @Json(name = "ratingRank")
        val ratingRank: Int?,
        @Json(name = "showType")
        val showType: String?,
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
        @Json(name = "titles")
        val titles: Titles?,
        @Json(name = "totalLength")
        val totalLength: Int?,
        @Json(name = "updatedAt")
        val updatedAt: String?,
        @Json(name = "userCount")
        val userCount: Int?,
        @Json(name = "youtubeVideoId")
        val youtubeVideoId: String?
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

        @Parcelize
        @JsonClass(generateAdapter = true)
        data class Titles(
            @Json(name = "en")
            val en: String?,
            @Json(name = "en_jp")
            val enJp: String?,
            @Json(name = "ja_jp")
            val jaJp: String?
        ): Parcelable

    }

}