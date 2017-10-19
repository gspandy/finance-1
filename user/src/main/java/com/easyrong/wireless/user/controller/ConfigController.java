package com.easyrong.wireless.user.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RefreshScope
public class ConfigController {
    @Value("${data.info}")
    private String info;

    @Value("${data.from}")
    private String from;

    @RequestMapping("/configInfo")
    public String hello() {
        return "info.profile: " + info + " info.from: " + from + " !";
    }
}
