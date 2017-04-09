package com.polaris.app.passenger.service.bo

import com.polaris.app.passenger.repository.StatusType

data class Shuttle(
        val shuttleID: Int,
        val name: String,
        val iconColor: String,
        val assignmentID: Int,
        val routeName: String,
        val status: StatusType,
        val stops: List<Stop>
)