package com.example.fitness_tracker_app.ui.screens

import android.service.controls.ControlsProviderService.TAG
import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.FilledTonalButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.fitness_tracker_app.ui.WorkoutViewModel
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
@Composable
fun WorkoutDetailScreen(
    workoutId: String?,
    navController: NavController,
    workoutViewModel: WorkoutViewModel
) {
    val workout = workoutViewModel.getWorkout(workoutId).value
    val db = Firebase.firestore

    workout?.let { currentWorkout ->
        Box (
            modifier = Modifier
                .background(Color.hsl(265f, 1f, 0.86f))
        ){
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(36.dp)
                    .background(Color.hsl(265f, 1f, 0.86f)) // Light gray background
            ) {
                Column(
                    modifier = Modifier
                        .background(Color.White)
                        .padding(16.dp)
                        .fillMaxWidth()
                ) {
                    Text(
                        text = "Workout Details",
                        style = TextStyle(
                            fontSize = 20.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color(0xFF37474F) // Dark blue text color
                        )
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                    Text(
                        text = "Type: ${currentWorkout.type}",
                        fontSize = 19.sp,
                        style = MaterialTheme.typography.bodyLarge,
                        color = Color.Black
                        )
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = "Duration: ${currentWorkout.duration} minutes",
                        fontSize = 19.sp,
                        style = MaterialTheme.typography.bodyLarge,
                        color = Color.Black
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = "Calories Burned: ${currentWorkout.caloriesBurned}",
                        fontSize = 19.sp,
                        style = MaterialTheme.typography.bodyLarge,
                        color = Color.Black
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                    FilledTonalButton(
                        onClick = {
                            workoutViewModel.addToFitnessGoal(currentWorkout)
                            // Add a new document with a generated ID
                            val workoutData = hashMapOf(
                                "type" to currentWorkout.type,
                                "duration" to currentWorkout.duration,
                                "caloriesBurned" to currentWorkout.caloriesBurned
                            )
                            db.collection("fitness_goals")
                                .add(workoutData)
                                .addOnSuccessListener { documentReference ->
                                    Log.d(
                                        TAG,
                                        "DocumentSnapshot added with ID: ${documentReference.id}"
                                    )
                                    navController.navigate("fitness_goal")
                                }
                                .addOnFailureListener { e ->
                                    Log.w(TAG, "Error adding document", e)
                                }
                        },
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(8.dp),
                        colors = ButtonDefaults.buttonColors(
                            Color(0xFF1976D2), // Blue button background
                            contentColor = Color.White
                        )
                    ) {
                        Text("Add to Fitness Goal")
                    }
                }
            }
        }
    }
}

