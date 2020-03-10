package com.thescore.nbateamviewer.di

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import com.thescore.nbateamviewer.api.NbaTeamService
import okhttp3.Cache
import okhttp3.OkHttpClient
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

/**
 * Created by Kanghee Lee
 */
val networkModule = module {

    single { Moshi.Builder().add(KotlinJsonAdapterFactory()).build() }
    single {
        val cacheSize = 1024.times(1024).times(5).toLong()
        val cache = Cache(androidContext().cacheDir, cacheSize)
        OkHttpClient.Builder()
            .cache(cache)
            .addInterceptor { chain ->
                var request = chain.request()
                request =
                    request.newBuilder().header("Cache-Control", "public, max-age=" + 5).build()
                chain.proceed(request)
            }.build()
    }
    single {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://raw.githubusercontent.com/scoremedia/nba-team-viewer/master/")
            .client(get())
            .addConverterFactory(MoshiConverterFactory.create(get())).build()
        retrofit.create(NbaTeamService::class.java)
    }
}