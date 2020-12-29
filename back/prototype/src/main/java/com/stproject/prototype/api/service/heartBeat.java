package com.stproject.prototype.api.service;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/heart-beat")
public class heartBeat {

    @GetMapping
    public String get() {
        return "heart Beating";
    }
}
