package com.polaris.app.passenger.service.impl

import com.polaris.app.passenger.repository.MapRepository
import com.polaris.app.passenger.service.MapService
import com.polaris.app.passenger.service.bo.Shuttle
import com.polaris.app.passenger.service.bo.Stop

class MapServiceImpl(val mapRepository: MapRepository): MapService {
    override fun retrieveShuttle(shuttleId: Int): Shuttle? {
        val shuttleEntity = this.mapRepository.findShuttle(shuttleId)

        if (shuttleEntity != null) {
            val stops = arrayListOf<Stop>()
            if (shuttleEntity.assignmentID != null) {
                val s = this.mapRepository.findStops(shuttleEntity.assignmentID)
                s.forEach {
                    val stop = Stop(
                            assignmentStopID = it.assignmentStopID,
                            assignmentID = it.assignmentID,
                            index = it.index,
                            stopId = it.stopId,
                            ETA = it.ETA,
                            ETD = it.ETD,
                            TOA = it.TOA,
                            TOD = it.TOD,
                            stopName = it.stopName,
                            address = it.address,
                            latitude = it.latitude,
                            longitude = it.longitude
                    )
                    stops.add(stop)
                }
            }

            var driver = this.mapRepository.getDriverInfo(shuttleEntity.driverID)

            var fname: String? = null
            var lname: String? = null

            if (driver != null)fname = driver.fname
            if (driver != null)lname = driver.lname

            return Shuttle(
                    shuttleID = shuttleEntity.shuttleID,
                    shuttleName = shuttleEntity.shuttleName,
                    driverFName = fname,
                    driverLName = lname,
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
        else
            return null
    }
}