package com.senai.abcgjl_smartcuisine_mobile.feature.auth.di

import com.senai.abcgjl_smartcuisine_mobile.feature.auth.data.remote.AuthApiService
import com.senai.abcgjl_smartcuisine_mobile.feature.auth.data.repository.AuthRepositoryImpl
import com.senai.abcgjl_smartcuisine_mobile.feature.auth.domain.repository.AuthRepository
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

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