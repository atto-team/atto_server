package com.atto.nimontoy


import com.atto.nimontoy.support.util.runWebflux
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.web.reactive.config.EnableWebFlux

@EnableWebFlux
@SpringBootApplication
open class NimonToyApplication

fun main(args: Array<String>) {
    runWebflux(NimonToyApplication::class, *args)
}
