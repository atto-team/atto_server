package com.atto.nimontoy.exception

/**
 * Created by 00700mm@gmail.com on 2019-09-23
 * Blog : http://gyejoong.tistory.com
 * Github : http://github.com/Gyejoon
 */
open class BadRequestException(
        override val message: String = "잘못된 요청입니다."
) : RuntimeException()