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
package com.epam.drill.github.action.service.impl

import com.epam.drill.github.action.controllers.DrillAdminService
import com.epam.drill.github.action.entity.CoverageResponse
import com.epam.drill.github.action.entity.SummaryResponse
import com.epam.drill.github.action.entity.agentId
import com.epam.drill.github.action.entity.baseBranch
import com.epam.drill.github.action.entity.baseVcsRef
import com.epam.drill.github.action.entity.currentBranch
import com.epam.drill.github.action.entity.currentVcsRef
import com.epam.drill.github.action.entity.groupId
import com.epam.drill.github.action.service.StatisticService
import mu.KotlinLogging
import retrofit2.Retrofit

class CommonStatisticService(retrofit: Retrofit) : StatisticService {

    private val statistics = retrofit.create(DrillAdminService::class.java)
    private val logger = KotlinLogging.logger {}

    override fun getRisks(): List<String> {
        logger.debug { "Get Risks" }
        return statistics.getRisks(groupId, agentId, currentBranch, currentVcsRef, baseBranch, baseVcsRef)
            .execute()
            .body()!!
    }

    override fun getCoverage(): CoverageResponse {
        logger.debug { "Get Coverage" }
        return statistics.getCoverage(groupId, agentId, currentVcsRef)
            .execute()
            .body()!!
    }

    override fun getSummary(): SummaryResponse {
        logger.debug { "Get Summary" }
        return statistics.getSummary(groupId, agentId, currentBranch, currentVcsRef, baseBranch, baseVcsRef)
            .execute()
            .body()!!
    }
}
