package com.polaris.app.passenger.service

import com.polaris.app.passenger.service.bo.Service

interface ServiceService{
    fun retrieveServices(): List<Service>
    fun retrieveServices(search: String): List<Service>
}