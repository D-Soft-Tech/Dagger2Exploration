package com.example.learningdatabinding.ui

import android.os.Bundle
import android.view.LayoutInflater
import androidx.appcompat.app.AppCompatActivity
import com.example.learningdatabinding.databinding.ActivityMainBinding
import com.example.learningdatabinding.di.Car
import com.example.learningdatabinding.di.DaggerCarComponent
import javax.inject.Inject

class MainActivity : AppCompatActivity() {
    private lateinit var dataBinding: ActivityMainBinding

    @Inject
    lateinit var car: Car

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Initializing the viewBinding variable
        dataBinding = ActivityMainBinding.inflate(LayoutInflater.from(this))
        val view = dataBinding.root
        setContentView(view)

        val carComponent = DaggerCarComponent.builder()
            .providesPowerCapacity(300)
            .providesEngineCapacity(100)
            .buildTheComponent()
        carComponent.injectCarIntoMainActivity(this)

        car.start()
    }
}
