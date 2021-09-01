package github.ardondev.apuri.repository.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import github.ardondev.apuri.network.ApiService
import github.ardondev.apuri.network.models.Manga

class MangaPagingDataSource(
    private val apiService: ApiService,
    private val search: String?,
    private val category: String?
) : PagingSource<Int, Manga>() {

    override fun getRefreshKey(state: PagingState<Int, Manga>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Manga> {
        try {
            val currentOffset = params.key ?: -10
            val limit = 10
            val offset = currentOffset + limit
            val limitMap = HashMap<String, Int>().apply { put("page[limit]", limit) }
            val offsetMap = HashMap<String, Int>().apply { put("page[offset]", offset) }
            val response = apiService.getAllManga(
                limit = limitMap,
                offset = offsetMap,
                search = if (search != null) {
                    HashMap<String, String>().apply { put("filter[text]", search) }
                } else {
                    HashMap()
                },
                category = if (category != null) {
                    HashMap<String, String>().apply { put("filter[categories]", category) }
                } else {
                    HashMap()
                }
            )

            val data = mutableListOf<Manga>()
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