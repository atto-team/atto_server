package com.atto.nimontoy

/**
 * Created by 00700mm@gmail.com on 2019-07-30
 * Blog : http://gyejoong.tistory.com
 * Github : http://github.com/Gyejoon
 */
class AuthenticationUser {
    companion object {
        val userPrincipal = ThreadLocal<UserPrincipal>()
    }
}