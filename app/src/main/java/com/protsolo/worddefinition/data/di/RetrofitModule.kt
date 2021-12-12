package com.protsolo.worddefinition.data.di

import com.protsolo.worddefinition.data.repository.remote.IRequestApi
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val retrofitModules = module {
    single { requestInterceptor() }
    single { requestOkHttpClient(get()) }
    single { requestRetrofit(get()) }
    single { createRequestApi(get()) }
}

fun requestInterceptor(): HttpLoggingInterceptor {
    val interceptor = HttpLoggingInterceptor()
    return interceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
}

fun requestOkHttpClient(interceptor: HttpLoggingInterceptor): OkHttpClient =
    OkHttpClient.Builder().addInterceptor(interceptor).build()

fun requestRetrofit(client: OkHttpClient): Retrofit =
    Retrofit.Builder()
        .baseUrl("https://api.dictionaryapi.dev/api/v2/entries/en/")
        .client(client)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

fun createRequestApi(retrofit: Retrofit): IRequestApi =
    retrofit.create(IRequestApi::class.java)