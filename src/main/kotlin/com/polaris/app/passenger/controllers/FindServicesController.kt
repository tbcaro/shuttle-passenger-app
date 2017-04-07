package com.polaris.app.passenger.controllers

import com.polaris.app.passenger.controllers.adapter.ServiceAdapter
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
    fun findServices(model: Model, filter: String?) : String {

        val service1 = ServiceAdapter()
        service1.serviceName = "Clarion Shuttle Company"
        service1.publicId = "clarionshuttle"
        service1.activeShuttleCount = 2

        val service2 = ServiceAdapter()
        service2.serviceName = "Pittsburgh Shuttle Company"
        service2.publicId = "pittshuttleco"
        service2.activeShuttleCount = 4

        val service3 = ServiceAdapter()
        service3.serviceName = "Pitt-Marriot Shuttle Service"
        service3.publicId = "marriotpitt"
        service3.activeShuttleCount = 1

        val serviceAdapters = arrayListOf(service1, service2, service3)
        model.addAttribute("serviceAdapters", serviceAdapters)

        if (serviceAdapters.size == 1) {
            return "redirect:/service-shuttles?publicId=${serviceAdapters[0].publicId}"
        } else {
            return "find-services"
        }
    }
}