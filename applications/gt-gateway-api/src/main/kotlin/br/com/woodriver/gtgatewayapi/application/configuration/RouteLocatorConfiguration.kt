package br.com.woodriver.gtgatewayapi.application.configuration

import org.springframework.cloud.gateway.route.RouteLocator
import org.springframework.cloud.gateway.route.builder.PredicateSpec
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Bean


@Configuration
class RouteLocatorConfiguration {

    @Bean
    fun customRouteLocator(builder: RouteLocatorBuilder): RouteLocator? {
        return builder.routes()
            .route(
                "gt-onboarding-api"
            ) { r: PredicateSpec ->
                r.path("/onboardings")
                    .uri("lb://gt-onboarding-api")
            }
            .build()
    }
}