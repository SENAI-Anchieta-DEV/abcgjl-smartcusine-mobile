package com.senai.abcgjl_smartcuisine_mobile.feature.auth.di

import com.google.android.datatransport.runtime.dagger.Binds
import com.google.android.datatransport.runtime.dagger.Module
import com.google.android.datatransport.runtime.dagger.Provides
import com.senai.abcgjl_smartcuisine_mobile.feature.auth.data.remote.AuthApiService
import com.senai.abcgjl_smartcuisine_mobile.feature.auth.data.repository.AuthRepositoryImpl

import retrofit2.Retrofit

@Module
@InstallIn(SingletonComponent::class)
abstract class AuthModule {

    @Binds
    @Singleton
    abstract fun bindAuthRepository(
        authRepositoryImpl: AuthRepositoryImpl
    ): AuthRepository

    companion object {
        @Provides
        @Singleton
        fun provideAuthApiService(
            retrofit: Retrofit
        ): AuthApiService = retrofit.create(AuthApiService::class.java)
    }
}