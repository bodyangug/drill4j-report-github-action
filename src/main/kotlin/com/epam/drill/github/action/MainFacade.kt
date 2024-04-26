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
package com.epam.drill.github.action

import com.epam.drill.github.action.entity.drillHost
import com.epam.drill.github.action.entity.drillPort
import com.epam.drill.github.action.service.impl.CommonPrCommentService
import com.epam.drill.github.action.service.impl.CommonStatisticService
import mu.KotlinLogging
import okhttp3.HttpUrl
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory


class MainFacade {
    private val retrofitGit =
        Retrofit.Builder()
            .baseUrl(HttpUrl.get("https://api.github.com"))
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .build()
    private val retrofitDrill =
        Retrofit.Builder()
            .baseUrl(HttpUrl.get("http://$drillHost:$drillPort"))
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .build()
    private val statisticService = CommonStatisticService(retrofitDrill)
    private val commentService = CommonPrCommentService(retrofitGit)
    private val logger = KotlinLogging.logger {}

    fun doReport() {
        logger.debug { "Starting doReport()." }
        try {
            //1. Get all params to do report
            val risks = statisticService.getRisks()
            val summary = statisticService.getSummary()
            val coverage = statisticService.getCoverage()

            //2. Generate comment report
            val comment = """
                Hello from Github Action.
                Risks: $risks
                Summary: $summary
                Coverage: $coverage
            """.trimIndent()

            //3. Send comment to pr
            println("Sending \n $comment")
            commentService.sendComment(comment)
        } catch (ex: Throwable) {
            val errorMessage = if (ex.message.isNullOrBlank()) "Unknown error: ${ex.javaClass.name}" else ex.message
            logger.error { "While making PR comments: $errorMessage." }
        }
    }
}
