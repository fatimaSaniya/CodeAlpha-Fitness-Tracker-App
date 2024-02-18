package com.example.fitness_tracker_app.data


data class Workout(
    val id: String = "",
    val type: String = "",
    val duration: Int = 0,
    val caloriesBurned: Int = 0,
)

data class ProgressData(
    val date: String,
    val workoutType: String,
    val duration: Int,
    val caloriesBurned: Int
)