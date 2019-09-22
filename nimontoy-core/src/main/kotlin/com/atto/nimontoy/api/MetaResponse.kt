package com.atto.nimontoy.api

import org.springframework.data.domain.Page

/**
 * Created by 00700mm@gmail.com on 2019-09-21
 * Blog : http://gyejoong.tistory.com
 * Github : http://github.com/Gyejoon
 */
data class MetaResponse(
        val pagination: Page<Any>
)