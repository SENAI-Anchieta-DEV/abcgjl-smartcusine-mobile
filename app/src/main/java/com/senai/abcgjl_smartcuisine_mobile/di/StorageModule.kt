package com.senai.abcgjl_smartcuisine_mobile.di

import android.content.Context
import com.senai.abcgjl_smartcuisine_mobile.core.datastore.SessionPreferenceStore
import com.senai.abcgjl_smartcuisine_mobile.core.datastore.ThemePreferenceStore
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object StorageModule {

    @Provides
    @Singleton
    fun provideThemePreferenceStore(
        @ApplicationContext context: Context
    ): ThemePreferenceStore = ThemePreferenceStore(context)

    @Provides
    @Singleton
    fun provideSessionPreferenceStore(
        @ApplicationContext context: Context
    ): SessionPreferenceStore = SessionPreferenceStore(context)
}