package com.gooselab.lolmbti.application

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.reactive.function.server.coRouter

@Configuration
open class LeagueOfLegendConfiguration {
    @Bean
    open fun provideLeagueOfLegendHandler() = LeagueOfLegendHandler()

    @Bean
    open fun mainRouter(lolHandler: LeagueOfLegendHandler) = coRouter {
        GET("/", lolHandler::ping)
        GET("/matches/by-summoner/{summonerName}", lolHandler::getMatchesBySummonerName)
    }
}