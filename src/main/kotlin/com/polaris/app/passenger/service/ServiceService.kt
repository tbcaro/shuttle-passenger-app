package com.polaris.app.passenger.service

import com.polaris.app.passenger.service.bo.Service

interface ServiceService{
    fun findServiceByPublicId(publicId: String): Service
    fun retrieveServices(): List<Service>
    fun retrieveServices(search: String): List<Service>
}