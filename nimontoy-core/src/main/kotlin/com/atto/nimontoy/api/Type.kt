package com.atto.nimontoy.api

import com.fasterxml.jackson.annotation.JsonValue

enum class Type(
        @get:JsonValue
        val title: String
) {
    FEED_LIST("feed.list"),
    FEED_DETAIL("feed.detail")
}