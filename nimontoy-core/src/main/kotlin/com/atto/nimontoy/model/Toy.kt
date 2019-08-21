package com.atto.nimontoy.model

import com.atto.nimontoy.jpa.BaseEntity
import javax.persistence.ManyToOne

/**
 * Created by 00700mm@gmail.com on 2019-08-13
 * Blog : http://gyejoong.tistory.com
 * Github : http://github.com/Gyejoon
 */
data class Toy(
        val name: String,
        @ManyToOne
        val collection: Collection
): BaseEntity()