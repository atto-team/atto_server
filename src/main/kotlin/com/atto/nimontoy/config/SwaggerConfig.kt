package com.atto.nimontoy.config

import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.reactive.config.WebFluxConfigurer
import springfox.documentation.builders.ParameterBuilder
import springfox.documentation.builders.PathSelectors
import springfox.documentation.builders.RequestHandlerSelectors
import springfox.documentation.schema.ModelRef
import springfox.documentation.service.BasicAuth
import springfox.documentation.spi.DocumentationType
import springfox.documentation.spring.web.plugins.Docket
import springfox.documentation.swagger2.annotations.EnableSwagger2WebFlux


@Configuration
@EnableSwagger2WebFlux
open class SwaggerConfig: WebFluxConfigurer {

    @Bean
    open fun apiDocket(): Docket {
        return Docket(DocumentationType.SWAGGER_2)
                .globalOperationParameters(arrayListOf(
                        ParameterBuilder()
                                .name("Authorization")
                                .description("Auth header")
                                .parameterType("header")
                                .defaultValue("Bearer ")
                                .required(false)
                                .modelRef(ModelRef("string")).build()
                ))
                .select()
                .apis(RequestHandlerSelectors.any())
                .paths(PathSelectors.any())
                .build()
                .securitySchemes(
                        arrayListOf(
                                BasicAuth("basic-auth-realm")
                        )
                )
    }

    override fun addResourceHandlers(registry: org.springframework.web.reactive.config.ResourceHandlerRegistry) {
        registry.addResourceHandler("/swagger-ui.html**")
                .addResourceLocations("classpath:/META-INF/resources/")

        registry.addResourceHandler("/webjars/**")
                .addResourceLocations("classpath:/META-INF/resources/webjars/")
    }
}
