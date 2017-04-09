package com.polaris.app.passenger.repository.entity

import java.math.BigDecimal
import java.time.LocalDateTime

data class StopEntity(
        val assignmentStopID: Int,
        val assignmentID: Int,
        val index: Int,
        val ETA: LocalDateTime,
        val ETD: LocalDateTime,
        val TOA: LocalDateTime,
        val TOD: LocalDateTime,
        val address: String,
        val latitude: BigDecimal,
        val longitude: BigDecimal
)