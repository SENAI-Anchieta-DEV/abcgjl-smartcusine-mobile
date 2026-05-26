package com.senai.abcgjl_smartcuisine_mobile.feature.signup.di

import com.senai.abcgjl_smartcuisine_mobile.feature.signup.data.remote.SignupApiService
import com.senai.abcgjl_smartcuisine_mobile.feature.signup.data.repository.SignupRepositoryImpl
import com.senai.abcgjl_smartcuisine_mobile.feature.signup.domain.repository.SignupRepository
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton
import retrofit2.Retrofit

@Module
@InstallIn(SingletonComponent::class)
abstract class SignupModule {

    @Binds
    @Singleton
    abstract fun bindSignupRepository(
        signupRepositoryImpl: SignupRepositoryImpl
    ): SignupRepository

    companion object {
        @Provides
        @Singleton
        fun provideSignupApiService(
            retrofit: Retrofit
        ): SignupApiService = retrofit.create(SignupApiService::class.java)
    }
}