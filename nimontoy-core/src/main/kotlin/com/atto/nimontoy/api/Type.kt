package com.atto.nimontoy.api

import com.fasterxml.jackson.annotation.JsonValue

enum class Type(
        @get:JsonValue
        val title: String
) {
    FEED_CELL("feed.cell"),
    FEED_COMMENT_CELL("feed.comment.cell")
}