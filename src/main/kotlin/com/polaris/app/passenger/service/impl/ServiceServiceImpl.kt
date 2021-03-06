package com.polaris.app.passenger.service.impl

import com.polaris.app.passenger.repository.ServiceRepository
import com.polaris.app.passenger.repository.entity.ServiceEntity
import com.polaris.app.passenger.service.ServiceService
import com.polaris.app.passenger.service.bo.Service

class ServiceServiceImpl(val serviceRepository: ServiceRepository): ServiceService{
    override fun findServiceByPublicId(publicId: String): Service {
        val serviceEntity = this.serviceRepository.findServiceByPublicId(publicId)
        return Service(
                serviceID = serviceEntity.serviceID,
                publicID = serviceEntity.publicID,
                serviceName = serviceEntity.serviceName,
                numShuttles = 0
        )
    }

    override fun retrieveServices(): List<Service> {
        val services = arrayListOf<Service>()
        val serviceEntities = this.serviceRepository.findServices()

        serviceEntities.forEach {
            val shuttles = this.serviceRepository.findShuttleNum(it.serviceID)
            val service = Service(
                    serviceID = it.serviceID,
                    publicID = it.publicID,
                    serviceName = it.serviceName,
                    numShuttles = shuttles
            )
            services.add(service)
        }
        return services
    }

    override fun retrieveServices(search: String): List<Service> {
        val services = arrayListOf<Service>()
        val serviceEntities = this.serviceRepository.findServices(search)

        serviceEntities.forEach {
            val shuttles = this.serviceRepository.findShuttleNum(it.serviceID)
            val service = Service(
                    serviceID = it.serviceID,
                    publicID = it.publicID,
                    serviceName = it.serviceName,
                    numShuttles = shuttles
            )
            services.add(service)
        }
        return services
    }
}