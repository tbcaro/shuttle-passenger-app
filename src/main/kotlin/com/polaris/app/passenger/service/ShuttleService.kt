package com.polaris.app.passenger.service

import com.polaris.app.passenger.service.bo.Shuttle

interface ShuttleService{
    fun retrieveShuttles(serviceID: Int): List<Shuttle>
}