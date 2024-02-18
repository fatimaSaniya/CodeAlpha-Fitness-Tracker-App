package com.example.fitness_tracker_app.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.fitness_tracker_app.ui.screens.ExerciseLoggerScreen
import com.example.fitness_tracker_app.ui.screens.FitnessGoalScreen
import com.example.fitness_tracker_app.ui.screens.WorkoutDetailScreen
import com.example.fitness_tracker_app.ui.screens.WorkoutListScreen

@Composable
fun Navigation() {
    val navController = rememberNavController()
    val workoutViewModel = remember { WorkoutViewModel() }

    NavHost(navController, startDestination = "workout_list") {

        composable("workout_list") {
            WorkoutListScreen(
                workoutViewModel = workoutViewModel,
                navController = navController
            )
        }

        composable("workout_detail/{workoutId}") { backStackEntry ->
            val workoutId = backStackEntry.arguments?.getString("workoutId")
            WorkoutDetailScreen(
                workoutId = workoutId,
                navController = navController,
                workoutViewModel = workoutViewModel
            )
        }

        composable("fitness_goal") {
            FitnessGoalScreen(
                workoutViewModel = workoutViewModel,
                navController = navController
            )
        }
        composable("exerciseLoggerScreen") {backStackEntry ->
            val workoutIdString = backStackEntry.arguments?.getString("workoutId")?.trim()
            val workoutId = if (workoutIdString.isNullOrEmpty()) {
                0
            } else {
                Integer.parseInt(workoutIdString)
            }
            ExerciseLoggerScreen(workoutViewModel, navController) }
    }
}
