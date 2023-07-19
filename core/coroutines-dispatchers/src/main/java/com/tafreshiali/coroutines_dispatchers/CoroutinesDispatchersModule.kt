package com.tafreshiali.coroutines_dispatchers

import com.tafreshiali.coroutines_dispatchers.qualifier.DefaultDispatcher
import com.tafreshiali.coroutines_dispatchers.qualifier.IoDispatcher
import com.tafreshiali.coroutines_dispatchers.qualifier.MainDispatcher
import com.tafreshiali.coroutines_dispatchers.qualifier.MainImmediateDispatcher
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers

/**
 * for more information see the google best practices article about coroutines =
 * "https://developer.android.com/kotlin/coroutines/coroutines-best-practices"*/

@InstallIn(SingletonComponent::class)
@Module
object CoroutinesDispatchersModule {


    @DefaultDispatcher
    @Provides
    fun providesDefaultDispatcher(): CoroutineDispatcher = Dispatchers.Default

    @IoDispatcher
    @Provides
    fun providesIoDispatcher(): CoroutineDispatcher = Dispatchers.IO

    @MainDispatcher
    @Provides
    fun providesMainDispatcher(): CoroutineDispatcher = Dispatchers.Main

    @MainImmediateDispatcher
    @Provides
    fun providesMainImmediateDispatcher(): CoroutineDispatcher = Dispatchers.Main.immediate
}
