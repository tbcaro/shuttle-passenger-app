package com.polaris.app.passenger.service.bo

data class Service(
    val serviceID: Int,
    val publicID: String,
    val serviceName: String,
    val numShuttles: Int
)