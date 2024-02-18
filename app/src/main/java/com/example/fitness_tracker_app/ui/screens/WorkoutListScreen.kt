package com.example.fitness_tracker_app.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.FilledTonalButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.fitness_tracker_app.ui.WorkoutViewModel

@Composable
fun WorkoutListScreen(
    workoutViewModel: WorkoutViewModel,
    navController: NavController,
) {
    val workouts by workoutViewModel.workouts.observeAsState(initial = emptyList())

    // Add a MaterialTheme to set the overall style
    MaterialTheme {
        Column(
            modifier = Modifier
                .background(Color.hsl(265f, 1f, 0.86f))
                .fillMaxSize()
                .padding(16.dp)
        ) {
            Row {
                Text(
                    text = "Workout for you",
                    style = MaterialTheme.typography.headlineLarge,
                    fontWeight = FontWeight.W700,
                    color = Color.White,
                    modifier = Modifier.padding(start = 12.dp, bottom = 16.dp)
                )
                FilledTonalButton(
                    onClick = { navController.navigate("exerciseLoggerScreen") },
                    modifier = Modifier
                      //  .fillMaxWidth()
                        .padding(start = 18.dp),
                    colors = ButtonDefaults.buttonColors(
                        Color(0xFF1976D2), // Blue button background
                        contentColor = Color.White
                    )
                ) {
                    Text("Add workout here!")
                }
            }

            LazyColumn {
                items(workouts) { workout ->
                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(8.dp),
                       // elevation = 4.dp, // Add a subtle shadow
                        shape = RoundedCornerShape(8.dp) // Rounded corners
                    ) {
                        Column(
                            modifier = Modifier
                                .background(Color.White)
                                .padding(16.dp)
                                .fillMaxWidth()
                                .clickable {
                                    navController.navigate("workout_detail/${workout.id}")
                                },
                        ) {
                            Text(
                                text = "Workout ID: ${workout.id}",
                                fontSize = 19.sp,
                                style = MaterialTheme.typography.bodyLarge,
                                color = Color.Black
                            )
                            Spacer(modifier = Modifier.height(8.dp))
                            Text(
                                text = "Workout Type: ${workout.type}",
                                fontSize = 19.sp,
                                style = MaterialTheme.typography.bodyMedium,
                                color = Color.Black
                            )
                            Spacer(modifier = Modifier.height(8.dp))
                            Text(
                                text = "Duration: ${workout.duration} minutes",
                                fontSize = 19.sp,
                                style = MaterialTheme.typography.bodyMedium,
                                color = Color.Black
                            )
                            Spacer(modifier = Modifier.height(8.dp))
                            Text(
                                text = "Calories Burned: ${workout.caloriesBurned}",
                                fontSize = 19.sp,
                                style = MaterialTheme.typography.bodyMedium,
                                color = Color.Black
                            )
                            Spacer(modifier = Modifier.height(8.dp))
                            Text(
                                text = "Click to know more!",
                                fontWeight = FontWeight.W700,
                                fontSize = 19.sp,
                                style = MaterialTheme.typography.bodyMedium,
                                color = Color.Black
                            )
                        }
                    }
                }
            }
        }
    }
}
