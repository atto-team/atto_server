package com.atto.nimontoy.model

import com.atto.nimontoy.jpa.BaseEntity

/**
 * Created by 00700mm@gmail.com on 2019-08-22
 * Blog : http://gyejoong.tistory.com
 * Github : http://github.com/Gyejoon
 */
data class Collection(
        val title: String,
        val season: Season
) : BaseEntity()


enum class Season {
    SUMMER
}