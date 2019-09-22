package com.atto.nimontoy

import com.atto.nimontoy.model.Role

/**
 * Created by 00700mm@gmail.com on 2019-07-30
 * Blog : http://gyejoong.tistory.com
 * Github : http://github.com/Gyejoon
 */
data class UserPrincipal(
        val id: Long,
        val name: String,
        val authorities: Collection<Role>
)
