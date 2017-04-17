package com.polaris.app.passenger.repository

import com.polaris.app.passenger.repository.entity.DriverEntity
import com.polaris.app.passenger.repository.entity.ShuttleEntity
import com.polaris.app.passenger.repository.entity.StopEntity

interface ShuttleRepository{
    fun findShuttles(publicId: String): List<ShuttleEntity>
    fun findStops(assignmentID: Int): List<StopEntity>
    fun getDriverInfo(driverID: Int): DriverEntity
}