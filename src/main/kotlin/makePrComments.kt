import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import okhttp3.HttpUrl
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.Body
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.Path

fun makePrComments(
    args: Array<String>,
    httpUrl: HttpUrl = HttpUrl.get(Common.URL_GITHUB),
): Int {

    debug("fun makePrComments: args=${args[Common.ARGS_INDEX_EVENT_FILE_PATH]}")

    val moshi = Moshi.Builder()
        .add(KotlinJsonAdapterFactory())
        .build()
    val retrofit = Retrofit.Builder()
        .baseUrl(httpUrl)
        .addConverterFactory(MoshiConverterFactory.create(moshi))
        .build()

    return try {
        val event = createGithubEvent(args[Common.ARGS_INDEX_EVENT_FILE_PATH], moshi)
        val comments = listOf(GithubPrComment("Hello world", event.pull_request.head.sha))
        val token = args[Common.ARGS_INDEX_TOKEN]
        makeComments(comments, token, event, retrofit)

        Common.EXIT_CODE_SUCCESS
    } catch (ex: Throwable) {
        val errorMessage = if (ex.message.isNullOrBlank())
            "Unknown error: ${ex.javaClass.name}" else
            ex.message
        error("while making PR comments: $errorMessage")
        Common.EXIT_CODE_FAILURE
    }
}


// make comment for the PR to github:
fun makeComments(
    comments: List<GithubPrComment>,
    token: String,
    event: GithubEvent,
    retrofit: Retrofit
) {

    debug("fun makeComments: comments=$comments|event=$event")

    val githubPrCommentsService = retrofit.create(GithubPrCommentsService::class.java)
    comments.forEach { comment ->
        githubPrCommentsService
            .createComment(
                "token $token",
                event.pull_request.user.login,
                event.repository.name,
                event.pull_request.number,
                comment
            )
            .execute()
    }
}

interface GithubPrCommentsService {
    @POST("/repos/{owner}/{repo}/pulls/{pull_number}/comments")
    fun createComment(
        @Header("Authorization") token: String,
        @Path("owner") owner: String,
        @Path("repo") repo: String,
        @Path("pull_number") number: Int,
        @Body comment: GithubPrComment
    ): Call<GithubPrCommentResponse>
}

data class GithubPrComment(
    val body: String,
    val commit_id: String,
    val side: String = "RIGHT"
)

class GithubPrCommentResponse(val url: String)
