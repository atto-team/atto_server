package com.atto.nimontoy

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Bean
import org.springframework.data.mongodb.config.EnableMongoAuditing
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean
import org.springframework.web.reactive.config.WebFluxConfigurer

/**
 * Created by 00700mm@gmail.com on 2019-07-12
 * Blog : http://gyejoong.tistory.com
 * Github : http://github.com/Gyejoon
 */
@SpringBootApplication
@EnableMongoAuditing
class NimonToyApplication : WebFluxConfigurer {

    @Bean
    fun validator() = LocalValidatorFactoryBean()

}

fun main(args: Array<String>) {
    runApplication<NimonToyApplication>(*args)
}