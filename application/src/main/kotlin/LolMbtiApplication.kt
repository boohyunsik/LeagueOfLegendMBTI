package com.gooselab.lolmbti.application

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
open class LolMbtiApplication

fun main(args: Array<String>) {
    runApplication<LolMbtiApplication>(*args)
}