package com.example.learningdatabinding.di

import com.example.learningdatabinding.ui.MainActivity
import dagger.BindsInstance
import dagger.Component
import javax.inject.Named

@Component(modules = [PetrolEngineModule::class])
interface CarComponent {
    fun getCar(): Car

    fun injectCarIntoMainActivity(mainActivity: MainActivity)

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun providesPowerCapacity(@Named("power")powerCapacity: Int): Builder

        @BindsInstance
        fun providesEngineCapacity(@Named("engine") engineCapacity: Int): Builder

        fun buildTheComponent(): CarComponent
    }
}
