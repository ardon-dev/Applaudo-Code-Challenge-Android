package github.ardondev.apuri.modules

import github.ardondev.apuri.ui.anime.AnimeViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {

    viewModel {
        AnimeViewModel(get())
    }

}