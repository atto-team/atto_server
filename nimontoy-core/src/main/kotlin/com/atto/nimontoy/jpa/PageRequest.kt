package com.atto.nimontoy.jpa

import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Sort
import javax.validation.constraints.NotEmpty

/**
 * Created by 00700mm@gmail.com on 2019-07-30
 * Blog : http://gyejoong.tistory.com
 * Github : http://github.com/Gyejoon
 */
data class PageRequest(
        @NotEmpty
        var page: Int = 1,
        @NotEmpty
        var size: Int = 10,
        val direction: Sort.Direction = Sort.DEFAULT_DIRECTION
) {
    init {
        val defaultSize = 10
        val maxSize = 50
        this.size = if (size > maxSize) defaultSize else size
    }

    fun of() = PageRequest.of(page - 1, size, direction, "createdDate")
}
