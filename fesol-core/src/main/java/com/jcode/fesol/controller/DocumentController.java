package com.jcode.fesol.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class DocumentController {

	@RequestMapping("/")
    public String welcome() {
        return "Welcome to RestTemplate Example.";
    }

}
