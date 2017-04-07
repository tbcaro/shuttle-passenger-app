package com.polaris.app.passenger.controller

import com.polaris.app.passenger.controller.adapter.AssignmentStopAdapter
import com.polaris.app.passenger.controller.adapter.ShuttleActivityAdapter
import com.polaris.app.passenger.controller.adapter.enums.ShuttleState
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.RequestMapping
import java.math.BigDecimal
import java.time.LocalTime

@Controller
class ServiceShuttlesController {

    @RequestMapping("/service-shuttles")
    fun serviceShuttles(model: Model, publicId: String ) : String {
        val stops = arrayListOf<AssignmentStopAdapter>()
        val stop1 = AssignmentStopAdapter()
        stop1.name = "Stop 1"
        stop1.order = 0
        stop1.address = "123 Baseball Stadium Road"
        stop1.lat = BigDecimal("41.192382")
        stop1.long = BigDecimal("-79.391694")
        stop1.estArriveTime = LocalTime.of(11, 30)
        stop1.estDepartTime = LocalTime.of(12, 0)

        val stop2 = AssignmentStopAdapter()
        stop2.name = "Stop 2"
        stop2.order = 1
        stop2.address = "123 Baseball Stadium Road"
        stop2.lat = BigDecimal("41.188791")
        stop2.long = BigDecimal("-79.394937")
        stop2.estArriveTime = LocalTime.of(12+1, 30)
        stop2.estDepartTime = LocalTime.of(12+2, 30)

        val stop3 = AssignmentStopAdapter()
        stop3.name = "Stop 3"
        stop3.order = 2
        stop3.address = "123 Baseball Stadium Road"
        stop3.lat = BigDecimal("41.207504")
        stop3.long = BigDecimal("-79.397200")
        stop3.estArriveTime = LocalTime.of(12+3, 0)
        stop3.estDepartTime = null

        stops.add(stop1)
        stops.add(stop2)
        stops.add(stop3)

        val activity2 = ShuttleActivityAdapter()
        activity2.shuttleId = 2
        activity2.driverName = "Tyler Holben"
        activity2.shuttleName = "Shuttle 2"
        activity2.shuttleStatus = ShuttleState.DRIVING
        activity2.shuttleLatitude = BigDecimal("41.214120")
        activity2.shuttleLongitude = BigDecimal("-79.384094")
        activity2.shuttleHeading = BigDecimal("275")
        activity2.assignmentReport?.assignmentStops = stops
        activity2.assignmentReport?.currentStop = 0

        val activity3 = ShuttleActivityAdapter()
        activity3.shuttleId = 3
        activity3.driverName = "Zach Kruise"
        activity3.shuttleName = "Shuttle 3"
        activity3.shuttleStatus = ShuttleState.AT_STOP
        activity3.shuttleLatitude = BigDecimal("41.194253")
        activity3.shuttleLongitude = BigDecimal("-79.392443")
        activity3.shuttleHeading = BigDecimal("10")
        activity3.assignmentReport?.assignmentStops = stops
        activity3.assignmentReport?.currentStop = 1

        val shuttleActivityAdapters = arrayListOf(activity2, activity3)

        model.addAttribute("shuttleActivityAdapters", shuttleActivityAdapters)

        if (shuttleActivityAdapters.size == 1) {
            return "redirect:/shuttle-map?shuttleId=${shuttleActivityAdapters[0].shuttleId}"
        } else {
            return "service-shuttles"
        }
    }
}