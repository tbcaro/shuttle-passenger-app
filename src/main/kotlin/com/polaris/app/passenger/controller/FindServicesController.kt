package com.polaris.app.passenger.controller

import com.polaris.app.passenger.controller.adapter.ServiceAdapter
import com.polaris.app.passenger.service.ServiceService
import com.polaris.app.passenger.service.bo.Service
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.RequestMapping

@Controller
class FindServicesController(private val serviceService: ServiceService) {

    @RequestMapping("/")
    fun root(model: Model) : String {
        return "redirect:/find-services"
    }

    @RequestMapping("/find-services")
    fun findServices(model: Model, filter: String?) : String {
        var services: List<Service>
        var serviceAdapters = arrayListOf<ServiceAdapter>()

        if (filter.isNullOrEmpty()) {
            services = serviceService.retrieveServices()
        } else {
            services = serviceService.retrieveServices(filter!!)
        }

        services.forEach {
            val serviceAdapter = ServiceAdapter()

            serviceAdapter.publicId = it.publicID
            serviceAdapter.serviceName = it.serviceName
            serviceAdapter.activeShuttleCount = it.numShuttles

            serviceAdapters.add(serviceAdapter)
        }

        model.addAttribute("serviceAdapters", serviceAdapters)

        if (filter.isNullOrBlank()) {
            model.addAttribute("filter", "")
        } else {
            model.addAttribute("filter", filter)
        }

        if (serviceAdapters.size == 1) {
            return "redirect:/service-shuttles?publicId=${serviceAdapters[0].publicId}"
        } else {
            return "find-services"
        }
    }
}