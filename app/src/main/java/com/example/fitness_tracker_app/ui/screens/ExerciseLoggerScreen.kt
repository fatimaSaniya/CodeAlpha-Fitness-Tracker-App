package com.example.fitness_tracker_app.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.FilledTonalButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.fitness_tracker_app.data.Workout
import com.example.fitness_tracker_app.ui.WorkoutViewModel

@Composable
fun ExerciseLoggerScreen(workoutViewModel: WorkoutViewModel = viewModel(), navController: NavController) {
    var workoutid by remember { mutableStateOf("") }
    var workoutName by remember { mutableStateOf("") }
    var workoutDuration by remember { mutableStateOf("") }
    var caloriesBurned by remember { mutableStateOf("") }

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(text = "Log your workout", style = MaterialTheme.typography.headlineMedium)
        Spacer(modifier = Modifier.height(16.dp))
        OutlinedTextField(
            value = workoutid,
            onValueChange = { workoutid = it },
            label = { Text("Workout Id") }
        )
        Spacer(modifier = Modifier.height(16.dp))
        OutlinedTextField(
            value = workoutName,
            onValueChange = { workoutName = it },
            label = { Text("Workout name") }
        )
        Spacer(modifier = Modifier.height(16.dp))
        OutlinedTextField(
            value = workoutDuration,
            onValueChange = { workoutDuration = it },
            label = { Text("Duration (in minutes)") }
        )
        Spacer(modifier = Modifier.height(16.dp))
        OutlinedTextField(
            value = caloriesBurned,
            onValueChange = { caloriesBurned = it },
            label = { Text("Calories Burned") }
        )
        FilledTonalButton(onClick = {
            val workout = Workout(id = workoutid, type = workoutName, duration = workoutDuration.toInt(), caloriesBurned = caloriesBurned.toInt())
            workoutViewModel.addWorkout(workout)
            navController.navigate("workout_list")
        }) {
            Text("Log Workout")
        }
    }
}
