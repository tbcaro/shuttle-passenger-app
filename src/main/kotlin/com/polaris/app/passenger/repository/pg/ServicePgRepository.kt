package com.polaris.app.passenger.repository.pg

import com.polaris.app.passenger.repository.ServiceRepository
import com.polaris.app.passenger.repository.entity.ServiceEntity
import com.polaris.app.passenger.repository.entity.ShuttleNumEntity
import org.springframework.jdbc.core.JdbcTemplate
import org.springframework.stereotype.Component

@Component
class ServicePgRepository(val db: JdbcTemplate): ServiceRepository
{

    override fun findServiceByPublicId(publicId: String): ServiceEntity {
        val serviceEntities = db.query(
                "SELECT * FROM service WHERE isactive = true AND publicid = ?;",
                arrayOf(publicId),
                {
                    resultSet, rowNum -> ServiceEntity(
                        resultSet.getInt("serviceid"),
                        resultSet.getString("publicid"),
                        resultSet.getString("servicename")
                    )
                }
        )
        if (serviceEntities.isNotEmpty()) return serviceEntities[0]
        else throw Exception("Service not found")
    }

    override fun findServices(): List<ServiceEntity> {
        val serviceEntities = db.query(
                "SELECT * FROM service WHERE isactive = true;",{
                    resultSet, rowNum -> ServiceEntity(
                        resultSet.getInt("serviceid"),
                        resultSet.getString("publicid"),
                        resultSet.getString("servicename")
                    )
                }
        )
        return serviceEntities
    }

    override fun findServices(search: String): List<ServiceEntity> {
        val param = "%${search}%"
        val serviceEntities = db.query(
                "SELECT * FROM service WHERE isactive = true AND lower(servicename) LIKE lower(?);",
                arrayOf(param),{
                    resultSet, rowNum -> ServiceEntity(
                        resultSet.getInt("serviceid"),
                        resultSet.getString("publicid"),
                        resultSet.getString("servicename")
                    )
                }
        )
        return serviceEntities
    }

    override fun findShuttleNum(serviceID: Int): Int {
        val shuttleNums = db.query(
                "SELECT count(shuttleid) AS c FROM shuttle_activity INNER JOIN shuttle ON (shuttle_activity.shuttleid = shuttle.\"ID\") WHERE shuttle.serviceid = ?;",
                arrayOf(serviceID),{
                    resultSet, rowNum -> ShuttleNumEntity(
                        resultSet.getInt("c")
                    )
                }
        )
        return shuttleNums[0].shuttleNum
    }
}