package com.atto.nimontoy.exception

/**
 * Created by 00700mm@gmail.com on 2019-09-23
 * Blog : http://gyejoong.tistory.com
 * Github : http://github.com/Gyejoon
 */
class AppException(
        override val code: Int = 500,
        override val message: String = "Internal Server Error."
) : ServiceException(
        code, message
)