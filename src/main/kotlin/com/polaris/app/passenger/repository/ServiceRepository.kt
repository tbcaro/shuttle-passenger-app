package com.polaris.app.passenger.repository

import com.polaris.app.passenger.repository.entity.ServiceEntity

interface ServiceRepository{
    fun findServiceByPublicId(publicId: String): ServiceEntity
    fun findServices(): List<ServiceEntity>
    fun findServices(search: String): List<ServiceEntity>
    fun findShuttleNum(serviceID: Int): Int
}