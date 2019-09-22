package com.atto.nimontoy.exception

/**
 * Created by 00700mm@gmail.com on 2019-09-23
 * Blog : http://gyejoong.tistory.com
 * Github : http://github.com/Gyejoon
 */
data class ErrorResponse(
        val message: String,
        val code: String,
        val status: Int
)