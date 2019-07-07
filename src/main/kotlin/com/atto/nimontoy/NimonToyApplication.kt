package com.atto.nimontoy


import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.stereotype.Controller

@SpringBootApplication
@Controller
class NimonToyApplication

fun main(args: Array<String>) {
    runApplication<NimonToyApplication>(*args)
}
