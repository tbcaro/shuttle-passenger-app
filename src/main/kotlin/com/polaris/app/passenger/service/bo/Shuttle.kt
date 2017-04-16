package com.polaris.app.passenger.service.bo

import com.polaris.app.passenger.controller.adapter.enums.ShuttleState

data class Shuttle(
        val shuttleID: Int,
        val name: String,
        val iconColor: String,
        val assignmentID: Int,
        val routeName: String,
        val status: ShuttleState,
        val stops: List<Stop>
)