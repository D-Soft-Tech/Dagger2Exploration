package com.example.learningdatabinding.di

interface Engine {
    fun startEngine() {}
    /*
        Note: I gave this function an empty body
        This is to follow the Interface segregation of SOLID Principle
        This principle says that:
            Clients in class that implements an interface should not
            be forced to implement any methods they don't need in the interface
     */
}
