package com.polaris.app.passenger.controller.adapter

import com.polaris.app.passenger.controller.adapter.enums.ShuttleState
import java.math.BigDecimal
import java.time.format.DateTimeFormatter


class ShuttleActivityAdapter {
    var shuttleId: Int = 0
    var shuttleName: String = ""
    var shuttleLatitude: BigDecimal = BigDecimal("0")
    var shuttleLongitude: BigDecimal = BigDecimal("0")
    var shuttleHeading: BigDecimal = BigDecimal("0")
    var driverName: String = ""
    var routeName: String = ""
    var servicePublicId: String = ""
    var shuttleStatus: ShuttleState = ShuttleState.NONE
    var assignmentReport: AssignmentReport? = AssignmentReport()
    val currentStopName: String? by lazy {
        if (this.assignmentReport == null) {
            null
        } else {
            if (this.assignmentReport!!.assignmentStops.isNotEmpty() && this.assignmentReport!!.assignmentStops.size > this.assignmentReport!!.currentStop) {
                this.assignmentReport!!.assignmentStops[this.assignmentReport!!.currentStop].name
            } else {
                null
            }
        }
    }
    val currentStopAddress: String? by lazy {
        if (this.assignmentReport == null) {
            null
        } else {
            if (this.assignmentReport!!.assignmentStops.isNotEmpty() && this.assignmentReport!!.assignmentStops.size > this.assignmentReport!!.currentStop) {
                this.assignmentReport!!.assignmentStops[this.assignmentReport!!.currentStop].address
            } else {
                null
            }
        }
    }
    val currentStopArrive: String? by lazy {
        if (this.assignmentReport == null) {
            null
        } else {
            if (this.assignmentReport!!.assignmentStops.isNotEmpty() && this.assignmentReport!!.assignmentStops.size > this.assignmentReport!!.currentStop) {
                var time = this.assignmentReport!!.assignmentStops[this.assignmentReport!!.currentStop].estArriveTime
                if (time == null) "--" else time.format(DateTimeFormatter.ofPattern("h:mm a"))
            } else {
                null
            }
        }
    }
    val currentStopDepart: String? by lazy {
        if (this.assignmentReport == null) {
            null
        } else {
            if (this.assignmentReport!!.assignmentStops.isNotEmpty() && this.assignmentReport!!.assignmentStops.size > this.assignmentReport!!.currentStop) {
                var time = this.assignmentReport!!.assignmentStops[this.assignmentReport!!.currentStop].estDepartTime
                if (time == null) "--" else time.format(DateTimeFormatter.ofPattern("h:mm a"))
            } else {
                null
            }
        }
    }
    val currentAssignmentLength: String? by lazy {
        if (this.assignmentReport == null) {
            null
        } else {
            if (this.assignmentReport!!.assignmentStops.isNotEmpty()) {
                this.assignmentReport?.assignmentStops?.size.toString()
            } else {
                null
            }
        }
    }
}