package com.polaris.app.passenger.controllers

import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.RequestMapping

@Controller
class ServiceShuttlesController {

    @RequestMapping("/service-shuttles")
    fun serviceShuttles(model: Model, publicId: String ) : String {
        return "service-shuttles"
    }
}