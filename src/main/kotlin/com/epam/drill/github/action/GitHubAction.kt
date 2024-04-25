package com.epam.drill.github.action

import mu.KotlinLogging

const val URL_GITHUB = "https://api.github.com"
private val logger = KotlinLogging.logger {}

fun main() {
    logger.info { "Starting GitHub Action" }
    MainFacade().doReport()
    logger.info { "Finishing GitHub Action" }
}
