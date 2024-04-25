package com.epam.drill.github.action.entity

data class GithubUser(val login: String)
data class GithubRepository(val name: String)
data class GithubPullRequestHead(val sha: String)
data class GithubPullRequest(val number: Int, val user: GithubUser, val head: GithubPullRequestHead)
data class GithubEvent(val pull_request: GithubPullRequest, val repository: GithubRepository)

class GithubPrCommentResponse(val url: String)
