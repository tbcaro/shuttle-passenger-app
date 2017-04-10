package com.polaris.app.passenger.controller.api

import com.polaris.app.passenger.controller.adapter.AssignmentStopAdapter
import com.polaris.app.passenger.controller.adapter.ShuttleActivityAdapter
import com.polaris.app.passenger.controller.adapter.enums.ShuttleState
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.math.BigDecimal
import java.time.LocalTime
import javax.servlet.http.HttpServletRequest


@RestController
@RequestMapping("/api")
class ApiController {
    @RequestMapping("/fetchShuttleActivity")
    fun fetchShuttleActivity(shuttleId: Int) : ResponseEntity<ShuttleActivityAdapter> {
        val activityAdapter = ShuttleActivityAdapter()



        // TEST
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

        activityAdapter.shuttleId = 1
        activityAdapter.driverName = "Travis Caro"
        activityAdapter.routeName = "Test Route 1"
        activityAdapter.shuttleName = "Shuttle 1A"
        activityAdapter.shuttleStatus = ShuttleState.DRIVING
        activityAdapter.shuttleLatitude = BigDecimal("41.211460")
        activityAdapter.shuttleLongitude = BigDecimal("-79.380963")
        activityAdapter.shuttleHeading = BigDecimal("30")
        activityAdapter.assignmentReport?.assignmentStops = stops
        activityAdapter.assignmentReport?.currentStop = 0
        activityAdapter.assignmentReport?.assignmentId = 1

        // END TEST


        return ResponseEntity(activityAdapter, HttpStatus.OK)
    }
}