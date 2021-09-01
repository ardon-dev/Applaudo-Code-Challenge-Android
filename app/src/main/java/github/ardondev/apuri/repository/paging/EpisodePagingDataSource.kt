package github.ardondev.apuri.repository.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import github.ardondev.apuri.network.ApiService
import github.ardondev.apuri.network.models.Episode

class EpisodePagingDataSource(
    private val apiService: ApiService,
    private val animeId: String
) : PagingSource<Int, Episode>() {

    override fun getRefreshKey(state: PagingState<Int, Episode>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Episode> {
        try {
            val currentOffset = params.key ?: -10
            val limit = 10
            val offset = currentOffset + limit
            val limitMap = HashMap<String, Int>().apply { put("page[limit]", limit) }
            val offsetMap = HashMap<String, Int>().apply { put("page[offset]", offset) }
            val response = apiService.getAllAnimeEpisodes(
                limit = limitMap,
                offset = offsetMap,
                id = animeId
            )

            val data = mutableListOf<Episode>()
            response.data?.let {
                data.addAll(it)
            }

            val prevKey = if (currentOffset == 10) null else currentOffset - 10
            return LoadResult.Page(
                data = data,
                prevKey = prevKey,
                nextKey = if (data.isEmpty()) null else currentOffset + 10
            )
        } catch (e: Exception) {
            return LoadResult.Error(e)
        }
    }

}