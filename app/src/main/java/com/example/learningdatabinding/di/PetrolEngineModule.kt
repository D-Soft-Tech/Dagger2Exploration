package com.example.learningdatabinding.di

import dagger.Module
import dagger.Provides

@Module
class PetrolEngineModule {

    @Provides
    fun providesPetrolEngine(petrolEngine: PetrolEngine): Engine {
        return petrolEngine
    }
}
