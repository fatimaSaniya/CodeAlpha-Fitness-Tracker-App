package com.example.fitness_tracker_app.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.FilledTonalButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.fitness_tracker_app.data.Workout
import com.example.fitness_tracker_app.ui.WorkoutViewModel

@Composable
fun FitnessGoalScreen(workoutViewModel: WorkoutViewModel, navController: NavController) {
    val fitnessGoalWorkouts by workoutViewModel.fitnessGoal.observeAsState(listOf())
    var selectedWorkout by remember { mutableStateOf<Workout?>(null) }
    Box(
        modifier = Modifier
            .fillMaxWidth()
            //  .padding(8.dp)
            .background(Color.hsl(265f, 1f, 0.86f))
    ) {
        if (selectedWorkout == null) {
            // Display the list of workouts
            Text(
                text = "YOUR FITNESS GOALS:",
                style = MaterialTheme.typography.headlineLarge,
                color = Color.White,
                modifier = Modifier.padding(23.dp)
            )
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(top = 76.dp, start = 16.dp, end = 16.dp)
            ) {
                items(fitnessGoalWorkouts) { workout ->
                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(8.dp)
                            .background(Color.White)
                    ) {
                        Column(
                            modifier = Modifier
                                .background(Color.White)
                                .fillMaxWidth()
                        ) {
                            Text(
                                modifier = Modifier.padding(
                                    top = 16.dp,
                                    start = 8.dp,
                                    bottom = 6.dp
                                ),
                                text = "Workout Details",
                                style = TextStyle(
                                    fontSize = 23.sp,
                                    color = Color.Black
                                )
                            )
                            Text(
                                text = "Workout ID: ${workout.id}",
                                modifier = Modifier.padding(8.dp),
                                style = TextStyle(
                                    fontSize = 16.sp,
                                    color = Color.Black
                                )
                            )
                            Text(
                                text = "Type: ${workout.type}",
                                modifier = Modifier.padding(8.dp),
                                style = TextStyle(
                                    fontSize = 16.sp,
                                    color = Color.Black
                                )
                            )
                            Text(
                                text = "Duration: ${workout.duration} minutes",
                                modifier = Modifier.padding(8.dp),
                                style = TextStyle(
                                    fontSize = 16.sp,
                                    color = Color.Black
                                )
                            )
                            Text(
                                text = "Calories Burned: ${workout.caloriesBurned}",
                                modifier = Modifier.padding(8.dp),
                                style = TextStyle(
                                    fontSize = 16.sp,
                                    color = Color.Black
                                )
                            )
                            FilledTonalButton(
                                onClick = { selectedWorkout = workout },
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(8.dp),
                                colors = ButtonDefaults.buttonColors(
                                    Color(0xFF1976D2), // Blue button background
                                    contentColor = Color.White
                                )
                            ) {
                                Text("View Progress")
                            }
                        }
                    }
                }
            }
        } else {
            // Display the progress screen for the selected workout
            ProgressScreen(
                workoutId = selectedWorkout!!.id,
                workoutViewModel = workoutViewModel,
                navController = navController
            )
        }
    }
}

@Composable
fun ProgressScreen(
    workoutId: String,
    workoutViewModel: WorkoutViewModel,
    navController: NavController,
) {
    val progressData by workoutViewModel.getProgressData(workoutId).observeAsState(initial = null)
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        if (progressData != null) {
            Text(text = "Date: ${progressData?.date}")
            Text(text = "Workout Type: ${progressData?.workoutType}")
            Text(text = "Duration: ${progressData?.duration} minutes")
            Text(text = "Calories Burned: ${progressData?.caloriesBurned}")
        } else {
            Text(
                text = "No progress data available for this workout.",
                modifier = Modifier.padding(45.dp),
                style = TextStyle(
                    fontSize = 23.sp,
                    color = Color.Black
                )
            )
        }
        FilledTonalButton(
            onClick = { navController.navigate("exerciseLoggerScreen") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            colors = ButtonDefaults.buttonColors(
                Color(0xFF1976D2), // Blue button background
                contentColor = Color.White
            )
        ) {
            Text("Go to Exercise Logger Screen")
        }
    }
}