package com.atto.nimontoy

import com.atto.nimontoy.util.loggerOf
import org.springframework.security.core.AuthenticationException
import org.springframework.security.web.AuthenticationEntryPoint
import org.springframework.stereotype.Component
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

/**
 * Created by 00700mm@gmail.com on 2019-07-19
 * Blog : http://gyejoong.tistory.com
 * Github : http://github.com/Gyejoon
 */
@Component
class UnauthorizedAuthenticationEntryPoint : AuthenticationEntryPoint {

    private val log = loggerOf(this::class)

    override fun commence(request: HttpServletRequest?, response: HttpServletResponse, e: AuthenticationException) {
        log.error("Responding with unauthorized error . Message = {}", e.message)
        response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Unauthorized error.")
    }


}