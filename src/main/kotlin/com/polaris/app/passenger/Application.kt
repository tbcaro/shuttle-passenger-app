package com.polaris.app.passenger

import com.polaris.app.passenger.repository.MapRepository
import com.polaris.app.passenger.repository.pg.MapPgRepository
import com.polaris.app.passenger.repository.pg.ServicePgRepository
import com.polaris.app.passenger.repository.pg.ShuttlePgRepository
import com.polaris.app.passenger.service.MapService
import com.polaris.app.passenger.service.ServiceService
import com.polaris.app.passenger.service.ShuttleService
import com.polaris.app.passenger.service.impl.MapServiceImpl
import com.polaris.app.passenger.service.impl.ServiceServiceImpl
import com.polaris.app.passenger.service.impl.ShuttleServiceImpl
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.context.annotation.Bean

@SpringBootApplication
open class Application {
    @Autowired
    lateinit var mapRepo: MapPgRepository

    @Autowired
    lateinit var shuttleRepo: ShuttlePgRepository

    @Autowired
    lateinit var serviceRepo: ServicePgRepository

    @Bean
    open fun mapService(): MapService {
        return MapServiceImpl(mapRepo)
    }

    @Bean
    open fun shuttleService(): ShuttleService {
        return ShuttleServiceImpl(shuttleRepo)
    }

    @Bean
    open fun serviceService(): ServiceService {
        return ServiceServiceImpl(serviceRepo)
    }
}

fun main(args: Array<String>) {
    SpringApplication.run(Application::class.java, *args)
}


