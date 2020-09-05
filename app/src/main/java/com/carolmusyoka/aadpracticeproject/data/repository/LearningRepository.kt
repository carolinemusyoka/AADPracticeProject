package com.carolmusyoka.aadpracticeproject.data.repository

import com.carolmusyoka.aadpracticeproject.data.api.ApiHelper

class LearningRepository (private val apiHelper: ApiHelper){
    suspend fun getLearningLeaders() = apiHelper.getLearningLeaders()
}