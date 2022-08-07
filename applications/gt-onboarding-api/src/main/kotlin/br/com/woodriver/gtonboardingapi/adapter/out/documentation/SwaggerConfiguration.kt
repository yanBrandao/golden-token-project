package br.com.woodriver.gtonboardingapi.adapter.out.documentation

import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Bean
import springfox.documentation.builders.PathSelectors

import springfox.documentation.builders.RequestHandlerSelectors

import springfox.documentation.spi.DocumentationType

import springfox.documentation.spring.web.plugins.Docket


@Configuration
class SwaggerConfiguration {
    @Bean
    fun api(): Docket {
        return Docket(DocumentationType.SWAGGER_2)
            .select()
            .apis(RequestHandlerSelectors.basePackage("br.com.woodriver.gtonboardingapi.adapter"))
            .paths(PathSelectors.any())
            .build()
    }
}