package com.polaris.app.passenger.service.impl

import com.polaris.app.passenger.repository.ShuttleRepository
import com.polaris.app.passenger.service.ShuttleService
import com.polaris.app.passenger.service.bo.Shuttle
import com.polaris.app.passenger.service.bo.Stop

class ShuttleServiceImpl(val shuttleRepository: ShuttleRepository): ShuttleService {
    override fun retrieveShuttles(publicId: String): List<Shuttle> {
        val shuttles = arrayListOf<Shuttle>()
        val shuttleEntities = this.shuttleRepository.findShuttles(publicId)

        shuttleEntities.forEach{
            val stops = arrayListOf<Stop>()
            val s = this.shuttleRepository.findStops(it.assignmentID)
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
            val shuttle = Shuttle(
                    shuttleID = it.shuttleID,
                    name = it.shuttleName,
                    iconColor = it.iconColor,
                    assignmentID = it.assignmentID,
                    routeName = it.routeName,
                    status = it.status,
                    stops = stops
            )
            shuttles.add(shuttle)
        }
        return shuttles
    }
}