package github.ardondev.apuri.modules

import github.ardondev.apuri.network.ApiService
import github.ardondev.apuri.repository.AppDataSource
import github.ardondev.apuri.repository.AppRepository
import org.koin.dsl.module

val appModule = module {

    fun createRepository(apiService: ApiService): AppRepository {
        return AppDataSource(apiService)
    }

    single { createRepository(get()) }

}