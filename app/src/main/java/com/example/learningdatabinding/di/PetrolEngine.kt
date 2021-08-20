package com.example.learningdatabinding.di

import javax.inject.Inject

class PetrolEngine @Inject constructor() : Engine {

    override fun startEngine() {
        println("${this.javaClass.simpleName} started")
    }
}
