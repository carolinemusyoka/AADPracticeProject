package com.carolmusyoka.aadpracticeproject.data.repository

import com.carolmusyoka.aadpracticeproject.data.api.ApiHelper
import com.carolmusyoka.aadpracticeproject.data.api.RetrofitBuilder.apiService

class SkillRepository(private val apiHelper: ApiHelper) {
    suspend fun getSkillLeaders() = apiService.getSkillLeaders()
}