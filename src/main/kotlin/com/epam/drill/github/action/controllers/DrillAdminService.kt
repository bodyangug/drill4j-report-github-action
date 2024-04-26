/**
 * Copyright 2020 - 2022 EPAM Systems
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.epam.drill.github.action.controllers

import com.epam.drill.github.action.entity.CoverageResponse
import com.epam.drill.github.action.entity.SummaryResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query


interface DrillAdminService {

    @GET("/api/metrics/coverage")
    fun getCoverage(
        @Query("groupId") groupId: String,
        @Query("agentId") agentId: String,
        @Query("currentVcsRef") currentVcsRef: String
    ): Call<CoverageResponse>

    @GET("/api/metrics//risks")
    fun getRisks(
        @Query("groupId") groupId: String,
        @Query("agentId") agentId: String,
        @Query("currentBranch") currentBranch: String,
        @Query("currentVcsRef") currentVcsRef: String,
        @Query("baseBranch") baseBranch: String,
        @Query("baseVcsRef") baseVcsRef: String
    ): Call<List<String>>

    @GET("/api/metrics//summary")
    fun getSummary(
        @Query("groupId") groupId: String,
        @Query("agentId") agentId: String,
        @Query("currentBranch") currentBranch: String,
        @Query("currentVcsRef") currentVcsRef: String,
        @Query("baseBranch") baseBranch: String,
        @Query("baseVcsRef") baseVcsRef: String,
    ): Call<SummaryResponse>

}
