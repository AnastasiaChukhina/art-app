package com.itis.artapp.di.modules

import androidx.viewbinding.BuildConfig
import com.itis.artapp.data.api.Api
import com.itis.artapp.di.modules.qualifiers.Logger
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.CallAdapter
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

private const val BASE_URL = "https://api.artic.edu/api/v1/artworks/"

@Module
@InstallIn(SingletonComponent::class)
class NetNodule {

    @Provides
    fun provideGsonConverter(): GsonConverterFactory = GsonConverterFactory.create()

    @Provides
    fun provideCallAdapterFactory(): CallAdapter.Factory = RxJava3CallAdapterFactory.create()

    @Provides
    @Logger
    fun provideLoggingInterceptor(): Interceptor {
        return HttpLoggingInterceptor()
            .setLevel(
                HttpLoggingInterceptor.Level.BODY
            )
    }

    @Provides
    fun providesOkhttp(
        @Logger loggingInterceptor: Interceptor
    ): OkHttpClient =
        OkHttpClient.Builder()
            .also {
                if (BuildConfig.DEBUG) {
                    it.addInterceptor(loggingInterceptor)
                }
            }
            .build()

    @Provides
    fun providesApi(
        okHttpClient: OkHttpClient,
        gsonConverter: GsonConverterFactory,
        callAdapterFactory: CallAdapter.Factory
    ): Api =
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(gsonConverter)
            .addCallAdapterFactory(callAdapterFactory)
            .build()
            .create(Api::class.java)
}
