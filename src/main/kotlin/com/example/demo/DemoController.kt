package com.example.demo

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController
import java.time.LocalTime

@RestController
class DemoController {

    @GetMapping("/api")
    fun api(
        @RequestBody request: DemoRequest,
    ): DemoResponse {
        return DemoResponse(
            time = LocalTime.now(),
        )
    }
}