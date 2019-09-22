package com.atto.nimontoy.exception

/**
 * Created by 00700mm@gmail.com on 2019-09-23
 * Blog : http://gyejoong.tistory.com
 * Github : http://github.com/Gyejoon
 */
open class ResourceNotFoundException(
        override val code: Int = 404,
        override val message: String = "데이터를 찾을 수 없습니다."
) : ServiceException(
        code, message
)