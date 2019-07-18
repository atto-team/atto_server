package com.atto.nimontoy.security

import org.springframework.http.HttpStatus
import org.springframework.security.core.AuthenticationException
import org.springframework.security.web.server.ServerAuthenticationEntryPoint
import org.springframework.stereotype.Component
import org.springframework.web.server.ServerWebExchange
import reactor.core.publisher.Mono

/**
 * Created by 00700mm@gmail.com on 2019-07-19
 * Blog : http://gyejoong.tistory.com
 * Github : http://github.com/Gyejoon
 */
@Component
class UnauthorizedAuthenticationEntryPoint : ServerAuthenticationEntryPoint {

    override fun commence(
            exchange: ServerWebExchange,
            e: AuthenticationException): Mono<Void> =
            Mono.fromRunnable {
                exchange.response.statusCode = HttpStatus.UNAUTHORIZED
            }
    
}