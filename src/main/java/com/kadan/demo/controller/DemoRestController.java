package com.kadan.demo.controller;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.LinkedHashMap;


@RestController
@RequestMapping("/")
public class DemoRestController {

    private final static Logger logger = LoggerFactory.getLogger(DemoRestController.class);

    @Autowired
    RestTemplate restTemplate;

    @Value("${client}")
    String client;

    @RequestMapping(value = "/circuit1", method = RequestMethod.GET)
    @HystrixCommand(fallbackMethod = "defaultReturn")
    public String getBookmarks() {
        logger.info("visting circuit1 ...");
        return restTemplate.getForObject(client,String.class);
        //return ResponseEntity.status(HttpStatus.OK).body("you are visiting circuit service 1 ...");

    }

    public String defaultReturn(){
        return "somethings' wrong in the downstream host ...";
    }


    }
