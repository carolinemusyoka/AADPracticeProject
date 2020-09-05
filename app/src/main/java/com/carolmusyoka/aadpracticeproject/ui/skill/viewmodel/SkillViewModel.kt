package com.carolmusyoka.aadpracticeproject.ui.skill.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.carolmusyoka.aadpracticeproject.data.repository.SkillRepository
import com.carolmusyoka.aadpracticeproject.utils.Resource
import kotlinx.coroutines.Dispatchers

class SkillViewModel(private val skillRepository: SkillRepository): ViewModel()  {
    fun getLearningLeaders() = liveData(Dispatchers.IO){
        emit(Resource.loading(data = null))
        try {
            emit(Resource.success(data = skillRepository.getSkillLeaders()))
        } catch (exception: Exception) {
            emit(Resource.error(data = null, message = exception.message ?: "Error Occurred!"))
        }
    }
}