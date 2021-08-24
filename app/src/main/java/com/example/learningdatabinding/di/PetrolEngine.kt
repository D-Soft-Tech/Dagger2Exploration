package com.example.learningdatabinding.di

import javax.inject.Inject
import javax.inject.Named

class PetrolEngine : Engine {
    var powerCapacity: Int
    var engineCapacity: Int

    @Inject constructor(@Named("power") powerCapacity: Int, @Named("engine")engineCapacity: Int) {
        this.powerCapacity = powerCapacity
        this.engineCapacity = engineCapacity
    }

    override fun startEngine() {
        println("${this.javaClass.simpleName} started whit capacity of: $powerCapacity \nEngine Capacity is: $engineCapacity")
    }
}
