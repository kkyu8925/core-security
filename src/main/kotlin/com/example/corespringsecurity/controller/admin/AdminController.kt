package com.example.corespringsecurity.controller.admin

import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping

@Controller
class AdminController {

    @GetMapping("/admin")
    fun home(): String {
        return "admin/home"
    }
}
