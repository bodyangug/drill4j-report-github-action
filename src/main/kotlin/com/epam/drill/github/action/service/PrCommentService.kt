package com.epam.drill.github.action.service

import com.epam.drill.github.action.utils.URL_GITHUB
import okhttp3.HttpUrl

interface PrCommentService {
    fun makePrComments(
        args: Array<String>,
        httpUrl: HttpUrl = HttpUrl.get(URL_GITHUB),
    ): Int
}
