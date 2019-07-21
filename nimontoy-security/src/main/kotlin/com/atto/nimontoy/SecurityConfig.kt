package com.atto.nimontoy

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.method.configuration.EnableReactiveMethodSecurity
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity
import org.springframework.security.config.web.server.ServerHttpSecurity
import org.springframework.security.web.server.SecurityWebFilterChain

/**
 * Created by 00700mm@gmail.com on 2019-07-18
 * Blog : http://gyejoong.tistory.com
 * Github : http://github.com/Gyejoon
 */
@Configuration
@EnableReactiveMethodSecurity
@EnableWebFluxSecurity
class SecurityConfig {

    @Bean
    fun securityFilterChain(
            http: ServerHttpSecurity,
            entryPoint: UnauthorizedAuthenticationEntryPoint
    ): SecurityWebFilterChain {
        http.httpBasic().disable()
                .formLogin().disable()
                .csrf().disable()
                .logout().disable()
        
        return http.build()
    }

}