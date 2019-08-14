package com.atto.nimontoy.dto


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
        val id: Long,
        val title: String,
        val contents: String
)