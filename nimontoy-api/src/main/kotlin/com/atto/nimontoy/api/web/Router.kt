package com.atto.nimontoy.api.web

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.MediaType
import org.springframework.web.reactive.function.server.router

/**
 * Created by 00700mm@gmail.com on 2019-07-12
 * Blog : http://gyejoong.tistory.com
 * Github : http://github.com/Gyejoon
 */
@Configuration
class Router {

    @Bean
    fun userRouter(userHandler: UserHandler) = router {
        ("/v1/users" and accept(MediaType.APPLICATION_JSON)).nest {
            GET("/", userHandler::all)
            POST("/", userHandler::create)
        }
    }

}