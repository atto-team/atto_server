package com.atto.nimontoy.api

import com.fasterxml.jackson.annotation.JsonInclude

@JsonInclude(JsonInclude.Include.NON_NULL)
open class ApiResponse(
        val data: BaseResponse
) {
    var meta: Any? = null
}

open class BaseResponse(
        val id: Long,
        val type: String,
        val scheme: String,
        val attributes: Any
)