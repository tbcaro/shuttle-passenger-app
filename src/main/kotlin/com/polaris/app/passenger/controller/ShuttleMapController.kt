package com.polaris.app.passenger.controller

import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.RequestMapping

@Controller
class ShuttleMapController {

    @RequestMapping("/shuttle-map")
    fun shuttleMap(model: Model, publicId: String, shuttleId: Int) : String {
        model.addAttribute("publicId", publicId)
        return "shuttle-map"
    }
}