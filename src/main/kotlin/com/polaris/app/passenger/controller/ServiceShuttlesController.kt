package com.polaris.app.passenger.controller

import com.polaris.app.passenger.controller.adapter.AssignmentReport
import com.polaris.app.passenger.controller.adapter.AssignmentStopAdapter
import com.polaris.app.passenger.controller.adapter.ShuttleActivityAdapter
import com.polaris.app.passenger.controller.adapter.enums.ShuttleState
import com.polaris.app.passenger.service.ServiceService
import com.polaris.app.passenger.service.ShuttleService
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.RequestMapping
import java.math.BigDecimal
import java.time.LocalTime

@Controller
class ServiceShuttlesController(private val shuttleService: ShuttleService,
                                private val serviceService: ServiceService
){

    @RequestMapping("/service-shuttles")
    fun serviceShuttles(model: Model, publicId: String ) : String {
        val shuttles = shuttleService.retrieveShuttles(publicId)
        val service = serviceService.findServiceByPublicId(publicId)
        val shuttleActivityAdapters = arrayListOf<ShuttleActivityAdapter>()

        shuttles.forEach {
            val shuttleActivity = ShuttleActivityAdapter()
            shuttleActivity.shuttleId = it.shuttleID
            shuttleActivity.driverName = "${it.driverFName} ${it.driverLName}"
            shuttleActivity.shuttleName = it.shuttleName
            shuttleActivity.routeName = it.routeName ?: "Custom Route"
            shuttleActivity.shuttleStatus = it.status
            shuttleActivity.shuttleLatitude = it.latitude
            shuttleActivity.shuttleLongitude = it.longitude
            shuttleActivity.shuttleHeading = it.heading

            val assignmentReport = AssignmentReport()
            assignmentReport.assignmentId = it.assignmentID
            assignmentReport.currentStop = it.index

            val assignmentStops = arrayListOf<AssignmentStopAdapter>()
            it.stops.forEach {
                val assignmentStop = AssignmentStopAdapter()

                assignmentStop.assingmentStopId = it.assignmentStopID
                assignmentStop.stopId = it.stopId
                assignmentStop.name = it.stopName ?: "Custom Stop"
                assignmentStop.address = it.address ?: ""
                assignmentStop.lat = it.latitude
                assignmentStop.long = it.longitude
                assignmentStop.order = it.index
                assignmentStop.estArriveTime = it.ETA?.toLocalTime()
                assignmentStop.estDepartTime = it.ETD?.toLocalTime()
                assignmentStop.actualArriveTime = it.TOA?.toLocalTime()
                assignmentStop.actualDepartTime = it.TOD?.toLocalTime()

                assignmentStops.add(assignmentStop)
            }
            shuttleActivity.assignmentReport?.assignmentStops = assignmentStops
            shuttleActivityAdapters.add(shuttleActivity)
        }

        model.addAttribute("serviceName", service.serviceName)
        model.addAttribute("shuttleActivityAdapters", shuttleActivityAdapters)
        model.addAttribute("publicId", publicId)

        if (shuttleActivityAdapters.size == 1) {
            return "redirect:/shuttle-map?publicId=${publicId}&shuttleId=${shuttleActivityAdapters[0].shuttleId}"
        } else {
            return "service-shuttles"
        }
    }
}