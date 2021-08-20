package com.example.learningdatabinding.di

import com.example.learningdatabinding.Wheels
import javax.inject.Inject

class Car @Inject constructor(var wheels: Wheels, var engine: Engine) {
    fun start() {
        engine.startEngine()
        println("Driving the car")
    }
}
