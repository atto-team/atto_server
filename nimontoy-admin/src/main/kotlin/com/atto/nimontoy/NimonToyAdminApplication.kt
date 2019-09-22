package com.atto.nimontoy

import de.codecentric.boot.admin.server.config.EnableAdminServer
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

/**
 * Created by 00700mm@gmail.com on 2019-07-13
 * Blog : http://gyejoong.tistory.com
 * Github : http://github.com/Gyejoon
 */
@SpringBootApplication
@EnableAdminServer
class NimonToyAdminApplication

fun main(args: Array<String>) {
    runApplication<NimonToyAdminApplication>(*args)
}