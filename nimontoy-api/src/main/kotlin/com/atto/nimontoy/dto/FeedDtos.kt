package com.atto.nimontoy.dto

import com.atto.nimontoy.api.BaseResponse
import com.atto.nimontoy.api.Type
import com.atto.nimontoy.api.BaseRequest
import com.atto.nimontoy.model.Feed
import com.atto.nimontoy.model.FeedComment
import com.fasterxml.jackson.annotation.JsonFormat
import java.time.LocalDateTime


/**
 * Created by 00700mm@gmail.com on 2019-08-15
 * Blog : http://gyejoong.tistory.com
 * Github : http://github.com/Gyejoon
 */
data class FeedCreateRequest(
        val title: String,
        val contents: String
) : BaseRequest<Feed> {
    override fun toEntity() = Feed(
            title = title,
            contents = contents
    )
}

data class FeedCommentCreateRequest(
        val contents: String
) : BaseRequest<FeedComment> {
    override fun toEntity() = FeedComment(
            contents = contents
    )
}

data class FeedResponse(
        override val id: Long,
        val title: String,
        val contents: String
) : BaseResponse {
    override val type: Type
        get() = Type.FEED_CELL
    override val scheme: String
        get() = "atto://feed/$id"
}

data class FeedCommentResponse(
        override val id: Long,
        val contents: String,
        @JsonFormat(shape = JsonFormat.Shape.STRING)
        val createdDate: LocalDateTime
) : BaseResponse {
    override val type: Type
        get() = Type.FEED_COMMENT_CELL
    override val scheme: String
        get() = "atto://feed/$id/comments"

}

fun Feed.toResponse() = FeedResponse(
        id = id,
        title = title,
        contents = contents
)

fun FeedComment.toResponse() = FeedCommentResponse(
        id = id,
        contents = contents,
        createdDate = createdDate
)