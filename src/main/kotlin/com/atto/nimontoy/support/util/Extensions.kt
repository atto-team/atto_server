package com.atto.nimontoy.support.util

import org.springframework.boot.SpringApplication
import org.springframework.boot.WebApplicationType
import org.springframework.boot.builder.SpringApplicationBuilder
import org.springframework.http.MediaType.*
import org.springframework.web.reactive.function.server.ServerResponse
import org.springframework.web.reactive.function.server.ServerResponse.permanentRedirect
import org.springframework.web.reactive.function.server.ServerResponse.seeOther
import java.net.URI
import kotlin.reflect.KClass

/**
 * Created by 00700mm@gmail.com on 2019-07-10
 * Blog : http://gyejoong.tistory.com
 * Github : http://github.com/Gyejoon
 */
fun run(type: KClass<*>, vararg args: String) = SpringApplication.run(type.java, *args)
fun runWebflux(type: KClass<*>, vararg args: String) = SpringApplicationBuilder()
        .sources(type.java)
        .web(WebApplicationType.REACTIVE)
        .run(*args)

// -------------------------
// Spring WebFlux extensions
// -------------------------


fun ServerResponse.BodyBuilder.json() = contentType(APPLICATION_JSON_UTF8)

fun ServerResponse.BodyBuilder.xml() = contentType(APPLICATION_XML)

fun ServerResponse.BodyBuilder.html() = contentType(TEXT_HTML)

fun permanentRedirect(uri: String) = permanentRedirect(URI(uri)).build()

fun seeOther(uri: String) = seeOther(URI(uri)).build()