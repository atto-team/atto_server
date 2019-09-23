package com.atto.nimontoy.api

/**
 * Created by 00700mm@gmail.com on 2019-09-23
 * Blog : http://gyejoong.tistory.com
 * Github : http://github.com/Gyejoon
 */
interface BaseRequest<out T> {
    fun toEntity(): T
}