package com.senai.abcgjl_smartcuisine_mobile.app.mqtt

import android.content.Context
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object MqttModule {

    @Provides
    @Singleton
    fun provideMqttClientManager(
        @ApplicationContext context: Context
    ): MqttClientManager {
        return MqttClientManager(context)
    }
}