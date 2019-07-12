package com.atto.nimontoy.admin.web

import com.atto.nimontoy.admin.model.User
import com.atto.nimontoy.admin.model.UserRepository
import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.server.ServerRequest
import org.springframework.web.reactive.function.server.ServerResponse
import org.springframework.web.reactive.function.server.ServerResponse.created
import org.springframework.web.reactive.function.server.ServerResponse.ok
import reactor.core.publisher.Mono
import java.net.URI

/**
 * Created by 00700mm@gmail.com on 2019-07-10
 * Blog : http://gyejoong.tistory.com
 * Github : http://github.com/Gyejoon
 */
@Component
class UserHandler(
        private val users: UserRepository
) {
    fun all(request: ServerRequest): Mono<ServerResponse> {
        return ok().body(users.findAll(), User::class.java)
    }

    fun create(request: ServerRequest): Mono<ServerResponse> =
            request.bodyToMono(User::class.java)
                    .flatMap { post -> users.save(post) }
                    .flatMap { id -> created(URI.create("/posts/$id")).build() }

}