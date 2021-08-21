package com.example.learningdatabinding.di

import javax.inject.Inject

class DieselEngine @Inject constructor() : Engine {

    override fun startEngine() {
        println("DieselEngine started")
    }
}
