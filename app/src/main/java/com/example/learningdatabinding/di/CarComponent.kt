package com.example.learningdatabinding.di

import com.example.learningdatabinding.ui.MainActivity
import dagger.Component

@Component(modules = [PetrolEngineModule::class])
interface CarComponent {
    fun getCar(): Car

    fun injectCarIntoMainActivity(mainActivity: MainActivity)
}
