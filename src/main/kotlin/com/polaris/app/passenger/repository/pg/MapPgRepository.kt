package com.polaris.app.passenger.repository.pg

import com.polaris.app.passenger.controller.adapter.enums.ShuttleState
import com.polaris.app.passenger.repository.MapRepository
import com.polaris.app.passenger.repository.entity.DriverEntity
import com.polaris.app.passenger.repository.entity.ShuttleEntity
import com.polaris.app.passenger.repository.entity.StopEntity
import org.springframework.jdbc.core.JdbcTemplate
import org.springframework.stereotype.Component
import java.math.BigDecimal
import java.sql.Timestamp

@Component
class MapPgRepository(val db: JdbcTemplate): MapRepository {
    override fun findShuttle(shuttleID: Int): ShuttleEntity? {
        val shuttleEntities = db.query(
                "SELECT * FROM shuttle INNER JOIN shuttle_activity ON (shuttle.\"ID\" = shuttle_activity.shuttleid) INNER JOIN assignment ON (shuttle_activity.assignmentid = assignment.assignmentid) WHERE shuttle.\"ID\" = ?;",
                arrayOf(shuttleID),{
                    resultSet, rowNum -> ShuttleEntity(
                        resultSet.getInt("ID"),
                        resultSet.getString("Name"),
                        resultSet.getInt("driverid"),
                        resultSet.getString("iconcolor"),
                        resultSet.getInt("assignmentid"),
                        resultSet.getString("routename"),
                        resultSet.getInt("Index"),
                        resultSet.getBigDecimal("heading"),
                        resultSet.getBigDecimal("latitude"),
                        resultSet.getBigDecimal("longitude"),
                        status = ShuttleState.valueOf(resultSet.getString("status"))
                    )
                }
        )
        if (shuttleEntities.isNotEmpty())
            return shuttleEntities[0]
        else
            return null
    }

    override fun findStops(assignmentID: Int): List<StopEntity> {
        val rows = db.queryForList(
                "SELECT * FROM assignment_stop LEFT OUTER JOIN stop ON (assignment_stop.stopid = stop.\"ID\") WHERE assignmentid = ? ORDER BY \"Index\";",
                assignmentID
        )

        val assignmentStopEntities = arrayListOf<StopEntity>()
        rows.forEach {
            var latitude: BigDecimal? = null
            var longitude: BigDecimal? = null
            var name = ""
            var address = ""
            var stopId = (it["stopid"]?.let { it as Int })

            if (stopId == null) {
                latitude = (it["assignment_stop.latitude"]?.let { it as BigDecimal }) ?: BigDecimal("0")
                longitude = (it["assignment_stop.longitude"]?.let { it as BigDecimal }) ?: BigDecimal("0")
                address = (it["assignment_stop.address"]?.let { it as String }) ?: ""
                name = "Custom Stop"
            } else {
                latitude = (it["stop.latitude"]?.let { it as BigDecimal }) ?: BigDecimal("0")
                longitude = (it["stop.longitude"]?.let { it as BigDecimal }) ?: BigDecimal("0")
                address = (it["stop.address"]?.let { it as String }) ?: ""
                name = (it["Name"]?.let { it as String }) ?: ""
            }

            val assignmentStop = StopEntity(
                    assignmentStopID = (it["assignment_stop_id"]?.let { it as Int }) ?: 0,
                    assignmentID = (it["assignmentid"]?.let { it as Int }) ?: 0,
                    index = (it["index"]?.let { it as Int }) ?: 0,
                    ETA = (it["estimatedtimeofarrival"]?.let { it as Timestamp })?.toLocalDateTime(),
                    ETD = (it["estimatedtimeofdeparture"]?.let { it as Timestamp })?.toLocalDateTime(),
                    TOA = (it["timeofarrival"]?.let { it as Timestamp })?.toLocalDateTime(),
                    TOD = (it["timeofdeparture"]?.let { it as Timestamp })?.toLocalDateTime(),
                    stopId = stopId,
                    stopName = name,
                    address = address,
                    latitude = latitude,
                    longitude = longitude
            )

            assignmentStopEntities.add(assignmentStop)
        }
        return assignmentStopEntities
    }

    override fun getDriverInfo(driverID: Int): DriverEntity {
        val driver = db.query(
                "SELECT fname, lname FROM \"user\" WHERE \"ID\" = ?",
                arrayOf(driverID), {
                    resultSet, rowNum -> DriverEntity(
                    resultSet.getString("fname"),
                    resultSet.getString("lname")
                )
            }
        )
        return driver[0]
    }
}