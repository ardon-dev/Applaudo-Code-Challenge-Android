package github.ardondev.apuri.repository

import github.ardondev.apuri.network.ApiService

class AppDataSource(
    private val apiService: ApiService
): AppRepository {
}