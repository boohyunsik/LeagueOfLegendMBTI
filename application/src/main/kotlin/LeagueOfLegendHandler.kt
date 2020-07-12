package com.gooselab.lolmbti.application

import org.springframework.web.reactive.function.server.ServerRequest
import org.springframework.web.reactive.function.server.ServerResponse
import org.springframework.web.reactive.function.server.bodyValueAndAwait

open class LeagueOfLegendHandler {

    private val domainService = DomainServiceFactory.createService()

    suspend fun ping(request: ServerRequest): ServerResponse =
        ServerResponse.ok().bodyValueAndAwait("Server is running!")

    suspend fun getMatchesBySummonerName(request: ServerRequest): ServerResponse {
        return ServerResponse.ok().bodyValueAndAwait(
            domainService.getRecentMatchesDetailBySummonerName(request.pathVariable("summonerName"), 2))
    }

}