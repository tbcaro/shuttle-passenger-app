package com.polaris.app.passenger.controllers

import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.RequestMapping

@Controller
class FindServicesController {

    val FIND_SERVICES_PAGE = "find-services"

    @RequestMapping("/find-services")
    fun findServices() : String {
        return FIND_SERVICES_PAGE
    }
}