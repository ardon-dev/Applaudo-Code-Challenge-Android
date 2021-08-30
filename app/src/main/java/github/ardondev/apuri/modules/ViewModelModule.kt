package github.ardondev.apuri.modules

import github.ardondev.apuri.ui.anime.AnimeViewModel
import github.ardondev.apuri.ui.anime_all.AnimeAllViewModel
import github.ardondev.apuri.ui.categories.CategoriesViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {

    viewModel {
        AnimeViewModel(get())
    }

    viewModel { (category: String) ->
        AnimeAllViewModel(get(), category)
    }

    viewModel {
        CategoriesViewModel(get())
    }

}