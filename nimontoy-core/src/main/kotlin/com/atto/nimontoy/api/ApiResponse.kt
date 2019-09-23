package com.atto.nimontoy.api

import com.fasterxml.jackson.annotation.JsonInclude

@JsonInclude(JsonInclude.Include.NON_NULL)
data class ApiResponse<out T>(
        val data: T,
        var meta: MetaResponse? = null
)

fun <T> ok(data: T) = ApiResponse(data)

fun <T> ok(data: T, meta: MetaResponse) = ApiResponse(data, meta)