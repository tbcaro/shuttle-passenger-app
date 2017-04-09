package com.polaris.app.passenger.repository.entity

import com.polaris.app.passenger.repository.StatusType

data class ShuttleEntity(
        val shuttleID: Int,
        val shuttleName: String,
        val iconColor: String,
        val assignmentID: Int,
        val routeName: String,
        val status: StatusType
)