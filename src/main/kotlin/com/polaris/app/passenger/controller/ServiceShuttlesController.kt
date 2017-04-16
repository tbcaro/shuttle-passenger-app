package com.polaris.app.passenger.controller

import com.polaris.app.passenger.controller.adapter.AssignmentStopAdapter
import com.polaris.app.passenger.controller.adapter.ShuttleActivityAdapter
import com.polaris.app.passenger.controller.adapter.enums.ShuttleState
import com.polaris.app.passenger.service.ShuttleService
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.RequestMapping
import java.math.BigDecimal
import java.time.LocalTime

@Controller
class ServiceShuttlesController(private val shuttleService: ShuttleService){

    @RequestMapping("/service-shuttles")
    fun serviceShuttles(model: Model, publicId: String ) : String {
        val shuttles = shuttleService.retrieveShuttles(publicId)
        val shuttleActivityAdapters = arrayListOf<ShuttleActivityAdapter>()

        shuttles.forEach {
            val shuttleActivity = ShuttleActivityAdapter()
            // TODO : Get shuttle activity information
            shuttleActivity.shuttleId = it.shuttleID
            //shuttleActivity.driverName = it.
            //shuttleActivity.shuttleName = it.
            shuttleActivity.shuttleStatus = it.status
            //shuttleActivity.shuttleLatitude = it.
            //shuttleActivity.shuttleLongitude = BigDecimal("-79.384094")
            //shuttleActivity.shuttleHeading = BigDecimal("275")
            //shuttleActivity.assignmentReport?.assignmentStops = stops
            //shuttleActivity.assignmentReport?.currentStop = 0

            // TBC : Example comment
//            val stops = arrayListOf<AssignmentStopAdapter>()
//            val stop1 = AssignmentStopAdapter()
//            stop1.name = "Stop 1"
//            stop1.order = 0
//            stop1.address = "123 Baseball Stadium Road"
//            stop1.lat = BigDecimal("41.192382")
//            stop1.long = BigDecimal("-79.391694")
//            stop1.estArriveTime = LocalTime.of(11, 30)
//            stop1.estDepartTime = LocalTime.of(12, 0)
        }

        model.addAttribute("shuttleActivityAdapters", shuttleActivityAdapters)
        model.addAttribute("publicId", publicId)

        if (shuttleActivityAdapters.size == 1) {
            return "redirect:/shuttle-map?publicId=${publicId}&shuttleId=${shuttleActivityAdapters[0].shuttleId}"
        } else {
            return "service-shuttles"
        }
    }
}