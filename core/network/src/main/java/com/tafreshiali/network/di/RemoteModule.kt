package com.tafreshiali.network.di

import com.tafreshiali.network.di.ApiConstance.APP_BASE_URL
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.ktor.client.HttpClient
import io.ktor.client.engine.HttpClientEngine
import io.ktor.client.plugins.HttpTimeout
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.defaultRequest
import io.ktor.serialization.gson.gson
import io.ktor.util.appendIfNameAbsent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RemoteModule {
    @Singleton
    @Provides
    fun provideKtorClient(appHttpClientEngine: HttpClientEngine): HttpClient =
        HttpClient(appHttpClientEngine) {
            install(ContentNegotiation) {
                gson(block = {
                    setPrettyPrinting()
                    enableComplexMapKeySerialization()
                    disableHtmlEscaping()
                })
            }

            install(HttpTimeout) {
                requestTimeoutMillis = 100000
            }

            defaultRequest {
                url(APP_BASE_URL)
                headers.appendIfNameAbsent(name = "accept", value = "application/json")
                headers.appendIfNameAbsent(
                    name = "Authorization",
                    value = "Bearer eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiI2Y2Q1YzlhYjM3MGI0NmRhN2QyN2VjZTE0NWM5OTI4OCIsInN1YiI6IjVkM2Q0ZTU0YTFkMzMyMDAxMjBlMzRiOSIsInNjb3BlcyI6WyJhcGlfcmVhZCJdLCJ2ZXJzaW9uIjoxfQ.Y5gnfKbUBEESHBPWNVTFTPtC8TBIPv31B75Xr1XGRyQ"
                )
            }
        }
}