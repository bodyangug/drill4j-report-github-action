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

val groupId: String = System.getenv("INPUT_GROUP-ID")
val agentId: String = System.getenv("INPUT_AGENT-ID")
val currentVcsRef: String = System.getenv("INPUT_CURRENT-VCS-REF")
val currentBranch: String = System.getenv("INPUT_CURRENT-BRANCH")
val baseBranch: String = System.getenv("INPUT_BASE-BRANCH")
val baseVcsRef: String = System.getenv("INPUT_BASE-VCS-REF")
val eventFilePath: String = System.getenv("GITHUB_EVENT_PATH")
val repoToken: String = System.getenv("INPUT_REPO-TOKEN")
val drillHost: String = System.getenv("INPUT_DRILL-HOST")
val drillPort: String = System.getenv("INPUT_DRILL-PORT")
