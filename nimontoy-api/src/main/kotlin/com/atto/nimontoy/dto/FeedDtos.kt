package com.atto.nimontoy.dto

import com.atto.nimontoy.api.BaseResponse
import com.atto.nimontoy.model.Feed


/**
 * Created by 00700mm@gmail.com on 2019-08-15
 * Blog : http://gyejoong.tistory.com
 * Github : http://github.com/Gyejoon
 */
data class FeedCreateRequest(
        val title: String,
        val contents: String
)

data class FeedResponse(
        val title: String,
        val contents: String
) {
    companion object {
        fun of(feed: Feed) = FeedResponse(
                feed.title,
                feed.contents
        )
    }
}