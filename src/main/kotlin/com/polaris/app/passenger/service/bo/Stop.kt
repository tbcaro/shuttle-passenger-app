package com.polaris.app.passenger.service.bo

import java.math.BigDecimal
import java.time.LocalDateTime

data class Stop(
        val assignmentStopID: Int,
        val assignmentID: Int,
        val index: Int,
        val stopId: Int?,
        val ETA: LocalDateTime,
        val ETD: LocalDateTime,
        val TOA: LocalDateTime,
        val TOD: LocalDateTime,
        val stopName: String?,
        val address: String?,
        val latitude: BigDecimal,
        val longitude: BigDecimal
)