package com.kmdhtsh.qiita0lgtmviewer

import com.kmdhtsh.qiita0lgtmviewer.repository.SearchRepository
import com.kmdhtsh.qiita0lgtmviewer.service.SearchService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import timber.log.Timber

@Module
@InstallIn(ApplicationComponent::class)
object ApplicationProvidesModule {

    @Provides
    fun provideHttpLoggingInterceptor(): HttpLoggingInterceptor {
        val logging = HttpLoggingInterceptor {
            Timber.tag("OkHttp").d(it)
        }
        logging.setLevel(HttpLoggingInterceptor.Level.BASIC)
        return logging
    }

    @Provides
    fun provideOkHttpClient(
        httpLoggingInterceptor: HttpLoggingInterceptor
    ): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(httpLoggingInterceptor)
            .build()
    }

    @Provides
    fun provideSearchService(okHttpClient: OkHttpClient): SearchService {
        return Retrofit.Builder()
            .baseUrl("https://qiita.com")
            .client(okHttpClient)
            .addConverterFactory(MoshiConverterFactory.create())
            .build()
            .create(SearchService::class.java)
    }

    @Provides
    fun provideSearchRepository(searchService: SearchService): SearchRepository {
        return SearchRepository(searchService)
    }
}
