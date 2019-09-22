package com.atto.nimontoy.dto

import com.fasterxml.jackson.annotation.JsonRootName

/**
 * Created by 00700mm@gmail.com on 2019-08-22
 * Blog : http://gyejoong.tistory.com
 * Github : http://github.com/Gyejoon
 */
@JsonRootName("")
data class CollectionResponse(
        val title: String
)