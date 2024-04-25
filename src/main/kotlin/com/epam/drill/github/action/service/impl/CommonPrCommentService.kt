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

import com.epam.drill.github.action.entity.GithubEvent
import com.epam.drill.github.action.entity.RequestBody
import com.epam.drill.github.action.service.GithubPrCommentsService
import com.epam.drill.github.action.service.PrCommentService
import mu.KotlinLogging
import retrofit2.Retrofit

class CommonPrCommentService(private val retrofit: Retrofit) : PrCommentService {
    private val logger = KotlinLogging.logger {}

    override fun sendComment(
        comment: String, token: String, event: GithubEvent
    ) {
        logger.info { "Start sending PR comment." }
        logger.debug { "Comment '$comment'." }
        retrofit.create(GithubPrCommentsService::class.java)
            .createComment(
                token = "token $token",
                owner = event.pull_request.user.login,
                repo = event.repository.name,
                issueNumber = event.pull_request.number,
                body = RequestBody(body = comment)
            ).execute()
        logger.info { "Finish sending PR comment." }
    }

}
