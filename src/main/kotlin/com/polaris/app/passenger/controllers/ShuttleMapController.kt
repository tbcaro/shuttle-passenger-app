package com.polaris.app.passenger.controllers

import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.RequestMapping

@Controller
class ShuttleMapController {

    val SHUTTLE_MAP_PAGE = "shuttle-map"

    @RequestMapping("/shuttle-map")
    fun shuttleMap() : String {
        return SHUTTLE_MAP_PAGE
    }
}