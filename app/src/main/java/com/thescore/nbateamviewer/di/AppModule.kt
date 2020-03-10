package com.thescore.nbateamviewer.di

import com.thescore.nbateamviewer.api.NbaTeamApi
import com.thescore.nbateamviewer.app.teamlist.TeamListViewModel
import com.thescore.nbateamviewer.domain.PlayerDtoMapper
import com.thescore.nbateamviewer.domain.TeamDtoMapper
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

/**
 * Created by Kanghee Lee
 */
val appModule = module {
    single { PlayerDtoMapper() }
    single { TeamDtoMapper(get()) }
    single { NbaTeamApi(get(), get()) }

    viewModel {
        TeamListViewModel(
            get()
        )
    }
}