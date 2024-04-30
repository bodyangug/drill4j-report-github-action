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
package com.epam.drill.github.action.entity

data class GithubUser(val login: String)
data class GithubRepository(val name: String, val owner: GithubUser)
data class GithubPullRequestHead(val sha: String)
data class GithubPullRequest(
    val number: Int,
    val user: GithubUser,
    val head: GithubPullRequestHead
)

data class GithubEvent(
    val pull_request: GithubPullRequest,
    val repository: GithubRepository
)

data class RequestBody(val body: String)

data class SummaryResponse(val coverage: String = "", val risks: String = "")
data class CoverageResponse(val coverage: String = "")

