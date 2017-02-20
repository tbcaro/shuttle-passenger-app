package com.polaris.app.passenger.controllers

import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.RequestMapping

@Controller
class ServiceShuttlesController {

    val SERVICE_SHUTTLES_PAGE = "service-shuttles"

    @RequestMapping("/service-shuttles")
    fun serviceShuttles() : String {
        return SERVICE_SHUTTLES_PAGE
    }
}