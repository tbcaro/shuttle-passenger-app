package com.polaris.app.passenger.controllers

import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.RequestMapping

@Controller
class ShuttleMapController {

    @RequestMapping("/shuttle-map")
    fun shuttleMap(model: Model) : String {
        return "shuttle-map"
    }
}