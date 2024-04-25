package com.epam.drill.github.action.service

import com.epam.drill.github.action.entity.GithubEvent

interface PrCommentService {
    fun sendComment(comment: String, token: String, event: GithubEvent)
}
