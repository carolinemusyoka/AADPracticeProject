package com.carolmusyoka.aadpracticeproject.ui.learning.viewmodel


import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.carolmusyoka.aadpracticeproject.data.api.ApiHelper
import com.carolmusyoka.aadpracticeproject.data.repository.LearningRepository
import com.carolmusyoka.aadpracticeproject.ui.learning.viewmodel.LearningViewModel

@Suppress("UNCHECKED_CAST")
class ViewModelFactory(private val apiHelper: ApiHelper) : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(LearningViewModel::class.java)) {
            return LearningViewModel(LearningRepository(apiHelper)) as T
        }
        throw IllegalArgumentException("Unknown class name")
    }

}
