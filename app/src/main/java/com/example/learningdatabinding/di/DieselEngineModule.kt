package com.example.learningdatabinding.di

import dagger.Module
import dagger.Provides

@Module
class DieselEngineModule {

    @Provides
    fun providesDieselEngine(dieselEngine: DieselEngine): DieselEngine = dieselEngine
}