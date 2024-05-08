package com.tafreshiali.presentation.di

import com.tafreshiali.data.remote.HomeMoviesRepositoryImpl
import com.tafreshiali.domain.repository.HomeMoviesRepository
import dagger.Binds
import dagger.Module
import dagger.Reusable
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
abstract class BinderModule {
    @Reusable
    @Binds
    abstract fun providesHomeMoviesRepository(homeMoviesRepositoryImpl: HomeMoviesRepositoryImpl): HomeMoviesRepository
}