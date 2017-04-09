package com.polaris.app.passenger.repository.pg

import com.polaris.app.passenger.repository.MapRepository
import com.polaris.app.passenger.repository.StatusType
import com.polaris.app.passenger.repository.entity.ShuttleEntity
import com.polaris.app.passenger.repository.entity.StopEntity
import org.springframework.jdbc.core.JdbcTemplate
import org.springframework.stereotype.Component

@Component
class MapPgRepository(val db: JdbcTemplate): MapRepository {
    override fun findShuttle(shuttleID: Int): ShuttleEntity {
        val shuttleEntities = db.query(
                "SELECT * FROM shuttle INNER JOIN shuttle_activity ON (shuttle.\"ID\" = shuttle_activity.shuttleid) INNER JOIN assignment ON (shuttle_activity.assignmentid = assignment.assignmentid) WHERE shuttle.\"ID\" = ?;",
                arrayOf(shuttleID),{
                    resultSet, rowNum -> ShuttleEntity(
                        resultSet.getInt("shuttle.ID"),
                        resultSet.getString("shuttle.Name"),
                        resultSet.getString("shuttle.iconcolor"),
                        resultSet.getInt("shuttle_activity.assignmentid"),
                        resultSet.getString("assignment.routename"),
                        status = StatusType.valueOf(resultSet.getString("shuttle_activity.status"))
                    )
                }
        )
        return shuttleEntities[0]
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
}