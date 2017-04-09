package com.polaris.app.passenger.repository

import com.polaris.app.passenger.repository.entity.ShuttleEntity
import com.polaris.app.passenger.repository.entity.StopEntity

interface ShuttleRepository{
    fun findShuttles(serviceID: Int): List<ShuttleEntity>
    fun findStops(assignmentID: Int): List<StopEntity>
}