package com.example.fitness_tracker_app.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.fitness_tracker_app.data.ProgressData
import com.example.fitness_tracker_app.data.Workout
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class WorkoutViewModel : ViewModel() {
    private val _workouts = MutableLiveData<List<Workout>>()
    val workouts: LiveData<List<Workout>> get() = _workouts
    private val _fitnessGoal = MutableLiveData<List<Workout>>(emptyList())
    val fitnessGoal: LiveData<List<Workout>> get() = _fitnessGoal
    private val _progressData = MutableLiveData<List<ProgressData>>()
    val progressData: LiveData<List<ProgressData>> get() = _progressData

    init {
        fetchWorkouts()
        fetchProgressData()
    }

    private fun fetchWorkouts() {
        val db = Firebase.firestore
        db.collection("workouts")
            .get()
            .addOnSuccessListener { result ->
                val workoutsList = result.map { document ->
                    document.toObject(Workout::class.java)
                }
                _workouts.value = workoutsList
            }
    }


    // Function to add a workout
    fun addWorkout(workout: Workout) {
        val currentList = workouts.value ?: emptyList()
        _workouts.value = currentList + workout
    }

    fun addToFitnessGoal(workout: Workout) {
        val currentList = _fitnessGoal.value ?: emptyList<Workout>()
        _fitnessGoal.value = currentList + listOf(workout)
    }

    // Function to get a workout by ID
    fun getWorkout(workoutId: String?): LiveData<Workout?> {
        val workout = _workouts.value?.find { it.id == workoutId }
        val workoutLiveData = MutableLiveData<Workout?>()
        workoutLiveData.value = workout
        return workoutLiveData
    }

    fun fetchProgressData() {
        val db = Firebase.firestore
        db.collection("progressData")
            .get()
            .addOnSuccessListener { result ->
                val progressDataList = result.map { document ->
                    document.toObject(ProgressData::class.java)
                }
                _progressData.value = progressDataList
            }
    }

    fun getProgressData(workoutId: String): LiveData<ProgressData?> {
        val progressDataLiveData = MutableLiveData<ProgressData?>()

        val db = Firebase.firestore
        db.collection("progressData")
            .document(workoutId)
            .get()
            .addOnSuccessListener { document ->
                val progressData = document.toObject(ProgressData::class.java)
                progressDataLiveData.value = progressData
            }
            .addOnFailureListener {
                progressDataLiveData.value = null
            }
        return progressDataLiveData
    }

}
