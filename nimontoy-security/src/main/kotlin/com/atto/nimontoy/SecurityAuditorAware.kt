package com.atto.nimontoy

import org.springframework.stereotype.Component

/**
 * Created by 00700mm@gmail.com on 2019-07-30
 * Blog : http://gyejoong.tistory.com
 * Github : http://github.com/Gyejoon
 */
@Component
class SecurityAuditorAware {
    fun getCurrentAuditor(): Long {
        val userPrincipal = AuthenticationUser.userPrincipal.get()
        return userPrincipal.id
    }
}