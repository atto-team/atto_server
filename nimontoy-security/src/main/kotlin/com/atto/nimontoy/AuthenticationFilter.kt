package com.atto.nimontoy

import com.atto.nimontoy.util.loggerOf
import com.google.firebase.auth.FirebaseAuth
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.util.StringUtils
import org.springframework.web.filter.OncePerRequestFilter
import javax.servlet.FilterChain
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

/**
 * Created by 00700mm@gmail.com on 2019-07-30
 * Blog : http://gyejoong.tistory.com
 * Github : http://github.com/Gyejoon
 */
class AuthenticationFilter : OncePerRequestFilter() {

    @Autowired
    private lateinit var tokenProvider: JwtTokenProvider

    private val log = loggerOf(this::class.java)

    override fun doFilterInternal(request: HttpServletRequest, response: HttpServletResponse, filterChain: FilterChain) {
        log.info("hello")
//        try {
//            val jwt = getJwtFromRequest(request)
//
//            if (StringUtils.hasText(jwt)) {
//
////                FirebaseAuth.getInstance().verifyIdToken(jwt)
//
//                //TODO. firebase or kakao token 검증 코드 구현
//                //TODO. 검증 후 redis에 토큰저장 로직 구현
//
////                SecurityContextHolder.getContext().authentication = authentication
//            }
//
//        } catch (e: Exception) {
//            log.error("Could not set user authentication in security context", e)
//            throw UnauthorizedException()
//        }

        filterChain.doFilter(request, response)

        log.info("what?")
    }

    private fun getJwtFromRequest(request: HttpServletRequest): String {
        val bearerToken = request.getHeader("Authorization")
        if (StringUtils.hasText(bearerToken) && bearerToken.startsWith("Bearer ")) {
            return bearerToken.substring(7, bearerToken.length)
        }
        return ""
    }

}