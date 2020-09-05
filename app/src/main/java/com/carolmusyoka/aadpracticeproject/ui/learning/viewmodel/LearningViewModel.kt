package com.carolmusyoka.aadpracticeproject.ui.learning.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.carolmusyoka.aadpracticeproject.data.repository.LearningRepository
import com.carolmusyoka.aadpracticeproject.utils.Resource
import kotlinx.coroutines.Dispatchers

class LearningViewModel(private val learningRepository: LearningRepository): ViewModel()  {
    fun getLearningLeaders() = liveData(Dispatchers.IO){
        emit(Resource.loading(data = null))
        try {
            emit(Resource.success(data = learningRepository.getLearningLeaders()))
        } catch (exception: Exception) {
            emit(Resource.error(data = null, message = exception.message ?: "Error Occurred!"))
        }
    }
}