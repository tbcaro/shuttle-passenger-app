package com.polaris.app.passenger.controllers

import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.RequestMapping

@Controller
class FindServicesController {

    @RequestMapping("/")
    fun root(model: Model) : String {
        return "redirect:/find-services"
    }

    @RequestMapping("/find-services")
    fun findServices(model: Model) : String {
        return "find-services"
    }
}