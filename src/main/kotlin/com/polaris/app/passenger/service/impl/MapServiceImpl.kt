package com.polaris.app.passenger.service.impl

import com.polaris.app.passenger.repository.MapRepository
import com.polaris.app.passenger.service.MapService
import com.polaris.app.passenger.service.bo.Shuttle
import com.polaris.app.passenger.service.bo.Stop

class MapServiceImpl(val mapRepository: MapRepository): MapService {
    override fun retrieveShuttle(shuttleId: Int): Shuttle {
        val shuttleEntity = this.mapRepository.findShuttle(shuttleId)

        val stops = arrayListOf<Stop>()
        val s = this.mapRepository.findStops(shuttleEntity.assignmentID)
        s.forEach {
            val stop = Stop(
                    assignmentStopID = it.assignmentStopID,
                    assignmentID = it.assignmentID,
                    index = it.index,
                    ETA = it.ETA,
                    ETD = it.ETD,
                    TOA = it.TOA,
                    TOD = it.TOD,
                    address = it.address,
                    latitude = it.latitude,
                    longitude = it.longitude
            )
            stops.add(stop)
        }

        return Shuttle(
                shuttleID = shuttleEntity.shuttleID,
                name = shuttleEntity.shuttleName,
                iconColor = shuttleEntity.iconColor,
                assignmentID = shuttleEntity.assignmentID,
                routeName = shuttleEntity.routeName,
                status = shuttleEntity.status,
                stops = stops
        )
    }
}