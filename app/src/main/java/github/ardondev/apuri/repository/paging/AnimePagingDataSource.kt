package github.ardondev.apuri.repository.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import github.ardondev.apuri.network.ApiService
import github.ardondev.apuri.network.models.Anime

class AnimePagingDataSource(
    private val apiService: ApiService,
    private val search: String?
) : PagingSource<Int, Anime>() {

    override fun getRefreshKey(state: PagingState<Int, Anime>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Anime> {
        try {
            val currentOffset = params.key ?: -10
            val limit = 10
            val offset = currentOffset + limit
            val limitMap = HashMap<String, Int>().apply { put("page[limit]", limit) }
            val offsetMap = HashMap<String, Int>().apply { put("page[offset]", offset) }
            val response = apiService.getAllAnime(
                limit = limitMap,
                offset = offsetMap,
                search = if (search != null) {
                    HashMap<String, String>().apply { put("filter[text]", search) }
                } else {
                    HashMap()
                }
            )

            val data = mutableListOf<Anime>()
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