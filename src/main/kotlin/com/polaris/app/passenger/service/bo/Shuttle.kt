package com.polaris.app.passenger.service.bo

import com.polaris.app.passenger.controller.adapter.enums.ShuttleState
import java.math.BigDecimal

data class Shuttle(
        val shuttleID: Int,
        val shuttleName: String?,
        val driverFName: String?,
        val driverLName: String?,
        val iconColor: String?,
        val assignmentID: Int?,
        val routeName: String?,
        val status: ShuttleState,
        val index: Int?,
        val heading: BigDecimal,
        val latitude: BigDecimal,
        val longitude: BigDecimal,
        val stops: List<Stop>
)