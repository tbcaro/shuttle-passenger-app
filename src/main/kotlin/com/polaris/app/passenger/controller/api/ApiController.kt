package com.polaris.app.passenger.controller.api

import com.polaris.app.passenger.controller.adapter.AssignmentReport
import com.polaris.app.passenger.controller.adapter.AssignmentStopAdapter
import com.polaris.app.passenger.controller.adapter.ShuttleActivityAdapter
import com.polaris.app.passenger.controller.adapter.enums.ShuttleState
import com.polaris.app.passenger.service.MapService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.math.BigDecimal
import java.time.LocalTime
import javax.servlet.http.HttpServletRequest


@RestController
@RequestMapping("/api")
class ApiController(private val mapService: MapService) {
    @RequestMapping("/fetchShuttleActivity")
    fun fetchShuttleActivity(shuttleId: Int) : ResponseEntity<ShuttleActivityAdapter> {
        val shuttle = mapService.retrieveShuttle(shuttleId)
        if (shuttle != null) {
            val activityAdapter = ShuttleActivityAdapter()

            activityAdapter.shuttleId = shuttle.shuttleID
            activityAdapter.driverName = "${shuttle.driverFName} ${shuttle.driverLName}"
            activityAdapter.routeName = shuttle.routeName ?: "Custom Route"
            activityAdapter.shuttleName = shuttle.shuttleName
            activityAdapter.shuttleStatus = shuttle.status
            activityAdapter.shuttleLatitude = shuttle.latitude
            activityAdapter.shuttleLongitude = shuttle.longitude
            activityAdapter.shuttleHeading = shuttle.heading

            val assignmentReport = AssignmentReport()
            assignmentReport.assignmentId = shuttle.assignmentID
            assignmentReport.currentStop = shuttle.index

            val assignmentStops = arrayListOf<AssignmentStopAdapter>()
            shuttle.stops.forEach {
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
            assignmentReport.assignmentStops = assignmentStops
            activityAdapter.assignmentReport = assignmentReport

            return ResponseEntity(activityAdapter, HttpStatus.OK)
        }
        else return ResponseEntity(null, HttpStatus.OK)
    }
}