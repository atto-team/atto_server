package com.atto.nimontoy.api

import com.fasterxml.jackson.annotation.JsonInclude

@JsonInclude(JsonInclude.Include.NON_NULL)
data class ApiResponse(
        val data: Any,
        var meta: MetaResponse? = null
)

fun ok(data: Any) = ApiResponse(data)

fun ok(data: Any, meta: MetaResponse) = ApiResponse(data, meta)