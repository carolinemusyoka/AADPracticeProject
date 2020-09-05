package com.carolmusyoka.aadpracticeproject.ui.skill.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.carolmusyoka.aadpracticeproject.data.api.ApiHelper
import com.carolmusyoka.aadpracticeproject.data.repository.SkillRepository

@Suppress("UNCHECKED_CAST")
class ViewModelFactory(private val apiHelper: ApiHelper) : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(SkillViewModel::class.java)) {
            return SkillViewModel(SkillRepository(apiHelper)) as T
        }
        throw IllegalArgumentException("Unknown class name")
    }

}