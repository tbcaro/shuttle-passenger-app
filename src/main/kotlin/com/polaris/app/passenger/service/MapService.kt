package com.polaris.app.passenger.service

import com.polaris.app.passenger.service.bo.Shuttle

interface MapService{
    fun retrieveShuttle(shuttleID: Int): Shuttle?
}