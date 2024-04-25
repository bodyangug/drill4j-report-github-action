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
