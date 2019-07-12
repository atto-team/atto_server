package com.atto.nimontoy.admin

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.data.mongodb.config.EnableMongoAuditing

/**
 * Created by 00700mm@gmail.com on 2019-07-12
 * Blog : http://gyejoong.tistory.com
 * Github : http://github.com/Gyejoon
 */
@SpringBootApplication
@EnableMongoAuditing
class NimonToyApplication

fun main(args: Array<String>) {
    runApplication<NimonToyApplication>(*args)
}