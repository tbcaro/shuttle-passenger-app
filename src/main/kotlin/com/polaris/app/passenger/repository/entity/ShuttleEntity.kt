package com.polaris.app.passenger.repository.entity

import com.polaris.app.passenger.controller.adapter.enums.ShuttleState
import java.math.BigDecimal

data class ShuttleEntity(
        val shuttleID: Int,
        val shuttleName: String?,
        val driverID: Int?,
        val iconColor: String?,
        val assignmentID: Int?,
        val routeName: String?,
        val index: Int?,
        val heading: BigDecimal,
        val latitude: BigDecimal,
        val longitude: BigDecimal,
        val status: ShuttleState
)