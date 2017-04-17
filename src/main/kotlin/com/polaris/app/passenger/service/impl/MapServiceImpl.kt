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

        val driver = this.mapRepository.getDriverInfo(shuttleEntity.driverID)

        return Shuttle(
                shuttleID = shuttleEntity.shuttleID,
                shuttleName = shuttleEntity.shuttleName,
                driverFName = driver.fname,
                driverLName = driver.lname,
                iconColor = shuttleEntity.iconColor,
                assignmentID = shuttleEntity.assignmentID,
                routeName = shuttleEntity.routeName,
                status = shuttleEntity.status,
                index = shuttleEntity.index,
                heading = shuttleEntity.heading,
                latitude = shuttleEntity.latitude,
                longitude = shuttleEntity.longitude,
                stops = stops
        )
    }
}