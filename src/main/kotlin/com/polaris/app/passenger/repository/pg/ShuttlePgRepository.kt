package com.polaris.app.passenger.repository.pg

import com.polaris.app.passenger.controller.adapter.enums.ShuttleState
import com.polaris.app.passenger.repository.ShuttleRepository
import com.polaris.app.passenger.repository.entity.DriverEntity
import com.polaris.app.passenger.repository.entity.ShuttleEntity
import com.polaris.app.passenger.repository.entity.StopEntity
import org.springframework.jdbc.core.JdbcTemplate
import org.springframework.stereotype.Component
import java.math.BigDecimal
import java.time.LocalDateTime

@Component
class ShuttlePgRepository(val db: JdbcTemplate): ShuttleRepository{
    override fun findShuttles(publicId: String): List<ShuttleEntity> {
        val shuttleEntities = db.query(
                "SELECT * FROM shuttle " +
                        "INNER JOIN shuttle_activity ON (shuttle.\"ID\" = shuttle_activity.shuttleid) " +
                        "INNER JOIN assignment ON (shuttle_activity.assignmentid = assignment.assignmentid) " +
                        "INNER JOIN service ON (shuttle.serviceid = service.serviceid) " +
                        "WHERE service.publicid = ? AND shuttle_activity.assignmentid != null;",
                arrayOf(publicId),{
                    resultSet, rowNum -> ShuttleEntity(
                        resultSet.getInt("ID"),
                        resultSet.getString("Name"),
                        resultSet.getInt("driverid"),
                        resultSet.getString("iconcolor"),
                        resultSet.getInt("assignmentid"),
                        resultSet.getString("routename"),
                        resultSet.getInt("index"),
                        resultSet.getBigDecimal("heading"),
                        resultSet.getBigDecimal("latitude"),
                        resultSet.getBigDecimal("longitude"),
                        status = ShuttleState.valueOf(resultSet.getString("status"))
                    )
                }
        )
        return shuttleEntities
    }

    override fun findStops(assignmentID: Int): List<StopEntity> {
        val stopEntities = db.query(
                "SELECT * FROM assignment_stop WHERE assignmentid = ?;",
                arrayOf(assignmentID),{
                    resultSet, rowNum -> StopEntity(
                        resultSet.getInt("assignment_stop_id"),
                        resultSet.getInt("assignmentid"),
                        resultSet.getInt("Index"),
                        resultSet.getTimestamp("estimatedtimeofarrival").toLocalDateTime(),
                        resultSet.getTimestamp("estimatedtimeofdeparture").toLocalDateTime(),
                        resultSet.getTimestamp("timeofarrival").toLocalDateTime(),
                        resultSet.getTimestamp("timeofdeparture").toLocalDateTime(),
                        resultSet.getString("address"),
                        resultSet.getBigDecimal("latitude"),
                        resultSet.getBigDecimal("longitude")
                    )
                }
        )
        return stopEntities
    }

    override fun getDriverInfo(driverID: Int): DriverEntity {
        val driver = db.query(
                "SELECT fname, lname FROM \"User\" WHERE \"ID\" = ?",
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